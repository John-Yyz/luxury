package com.luxury.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luxury.config.DomainData;
import com.luxury.redis.RedisUtils;
import com.luxury.mapper.EnterpriseUserMapper;
import com.luxury.mapper.UserSourceMapper;
import com.luxury.model.EnterpriseUser;
import com.luxury.model.UserSource;
import com.luxury.redis.RedisKeys;
import com.luxury.request.GetPhoneNumberReq;
import com.luxury.request.OuthSourceEnt;
import com.luxury.request.WeChatRegisterReq;
import com.luxury.service.AppletWxService;
import com.luxury.service.IUserInfoRegistService;
import com.luxury.service.ReqGetService;
import com.luxury.service.WechatService;
import com.luxury.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/17 1:10
 */
@Service
@Slf4j
public class UserInfoRegistServiceImpl implements IUserInfoRegistService {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    UserSourceMapper userSourceMapper;
    @Autowired
    WechatService webChatService;
    @Autowired
    AppletWxService appletWxService;
    @Autowired
    EnterpriseUserMapper enterpriseUserMapper;

    @Autowired
    ReqGetService reqGetService;

    @Override
    public JsonResult weChatRegister(WeChatRegisterReq req, OuthSourceEnt outhSourceEnt) {
        // 请求用户来源（1=微信；4=微信小程序；5=支付宝小程序）
        Integer platype = outhSourceEnt.getPlatype();

        if (Objects.isNull(platype)) {
            return JsonResult.error(ErrorCode.FAILED.getCode(), "平台无权限操作");
        }

        if (null != platype && platype == 0) {
            return JsonResult.error(ErrorCode.FAILED.getCode(), "平台无权限操作");
        }

        // 用户手机号
        String userMobile = req.getUserMobile();

        // 限制频繁点击
        String redisKey = RedisKeys.REQUEST_REGISTER_COUNT_KEY + userMobile;
        if (redisUtils.exists(redisKey)) {
            return JsonResult.error(ErrorCode.FAILED.getCode(), "你已经请求了，请不要重复请求");
        } else {
            redisUtils.set(redisKey, userMobile, 5);
        }

        String openId = req.getOpenId();
        if (StringUtils.isBlank(openId)) {
            return JsonResult.error(ErrorCode.FAILED.getCode(), "获取不到openid");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userSource", platype);
        params.put("openId", openId);
        UserSource exitst_userSourceEnt = userSourceMapper.findUserIsRegistByOpenId(params);
        if (!Objects.isNull(exitst_userSourceEnt)) {
            log.info("出现不同跳过检测注册接口，直接访问注册接口的请求, userMobile ---》 " + userMobile + " <-openId->" + openId);
            return JsonResult.error(ErrorCode.FAILED.getCode(), "该手机号已注册");
        }

        String wxUnionid = null;

        switch (platype) {
            case 1:
                if (redisUtils.exists(openId)) {
                    wxUnionid = Objects.isNull(redisUtils.get(openId))?"": (String) redisUtils.get(openId);
                } else {
                    wxUnionid = webChatService.getUnionid(openId);
                }
                break;
            case 4:

                if (redisUtils.exists(openId)) {
                    wxUnionid = Objects.isNull(redisUtils.get(openId))?"": (String) redisUtils.get(openId);
                }

                // 小程序私钥
                String sessionKey = req.getSessionKey();

                // 小程序-加密数据
                String encryunionid = req.getEncryunionid();

                // 小程序-加密算法的初始向量
                String encryvi = req.getEncryvi();

                String jscode = req.getJscode();

                /** 获取用户信息 */
                if (StringUtils.isNotBlank(encryvi) && StringUtils.isNotBlank(encryunionid)) {
                    String wbInfo = WechatUtil.decryptData(encryunionid, sessionKey, encryvi);
                    if (StringUtils.isNotBlank(wbInfo)) {
                        JSONObject json = JSON.parseObject(wbInfo);
                        String cuur_openid = json.getString("openId");
                        String cuur_unionId = json.getString("unionId");

                        if (!cuur_openid.equals(openId)) {
                            log.error("出现用户一键登录openid不一致情况--》 cuur_openid: " + cuur_openid + ",openid: " + openId);
                        }
                        if (StringUtils.isNotBlank(cuur_unionId)) {
                            wxUnionid = cuur_unionId;
                        }
                    } else {
                        if (StringUtils.isBlank(wxUnionid) && StringUtils.isNotBlank(jscode)) {
                            Map<String, Object> resultMap = appletWxService.getWI(jscode);
                            if (resultMap != null && !resultMap.isEmpty()) {
                                String cuur_unionid = resultMap.get("unionid") == null ? null
                                        : (String) resultMap.get("unionid");
                                if (StringUtils.isNotBlank(cuur_unionid)) {
                                    wxUnionid = cuur_unionid;
                                }
                            }
                        }
                    }
                } else {
                    log.info("用户小程序手机注册无数据---> " + JSONObject.toJSONString(req));
                }
                break;
            default:
                break;
        }

        Map<String,Object> userMap = new HashMap<>(1);
        userMap.put("userMobile",userMobile);
        // 检查该用户是否存在
        String token = null;
        EnterpriseUser user = enterpriseUserMapper.selectByParams(userMap);
        String userId = null;
        if (!Objects.isNull(user)) {

            userId = user.getUserId();

            params.clear();
            params.put("userMobile", userMobile);
            params.put("userSource", platype);
            UserSource cuur_userSourceEnt = userSourceMapper.selectByUserMobileAndSource(params);

            // 生成userToken
            String userToken = JwtHelper.createJWT(userId, platype, userMobile, DomainData.USERTTLMILLIS);
            token = userToken;

            if (Objects.isNull(cuur_userSourceEnt)) {

                // 如果源已存在，则默认更新为最新数据
                params.clear();
                params.put("userId", userId);
                params.put("userSource", platype);
                UserSource userSourceEntBak = userSourceMapper.selectByUserSourceAndUserId(params);
                if (Objects.isNull(userSourceEntBak)) {

                    // 插入用户注册来源数据
                    UserSource userSourceEnt = new UserSource();
                    userSourceEnt.setUserMobile(userMobile);
                    userSourceEnt.setUserId(userId);
                    userSourceEnt.setUserSource(platype.shortValue());
                    userSourceEnt.setUserToken(userToken);
                    userSourceEnt.setObjIds(openId);
                    userSourceEnt.setCreateTime(DateUtils.getTime());
                    userSourceEnt.setWxUnionId(wxUnionid);
                    userSourceMapper.insertSelective(userSourceEnt);

                } else {
                    userSourceEntBak.setUserToken(userToken);
                    userSourceEntBak.setObjIds(openId);
                    userSourceEntBak.setUserMobile(userMobile);
                    userSourceEntBak.setWxUnionId(wxUnionid);
                    userSourceEntBak.setUpdateTime(DateUtils.getTime());
                    userSourceMapper.updateByPrimaryKeySelective(userSourceEntBak);
                }

            } else {

                String objIds = cuur_userSourceEnt.getObjIds();

                if (null != objIds && !objIds.equals(openId)) {

                    log.info(userMobile + ": 手动注册时, 该手机号已绑定了别的账号, userid- " + userId + " <-objIds->" + objIds
                            + " <-openId->" + openId);

                    cuur_userSourceEnt.setObjIds(openId);
                }

                // 更新token
                cuur_userSourceEnt.setUserToken(userToken);
                cuur_userSourceEnt.setWxUnionId(wxUnionid);
                userSourceMapper.updateByPrimaryKeySelective(cuur_userSourceEnt);

            }
        } else {

            EnterpriseUser userInfoEnt = new EnterpriseUser();
            userInfoEnt.setUserName(userMobile);
            userInfoEnt.setUserMobile(userMobile);
            userInfoEnt.setNickName("");
            userInfoEnt.setUserSource(platype);
            userInfoEnt.setRegDate(DateUtils.getTime());
            userInfoEnt.setUserState(1);
            userInfoEnt.setUserImg("/php/head_photo/default_head_photo.png");
            int result = enterpriseUserMapper.insertSelective(userInfoEnt);
            if (result == 1) {
                userId = userInfoEnt.getUserId();
            }

            // 生成userToken
            String userToken = JwtHelper.createJWT(userId, platype, userMobile, DomainData.USERTTLMILLIS);
            token = userToken;

            // 插入用户注册来源数据
            UserSource userSourceEnt = new UserSource();
            userSourceEnt.setUserMobile(userMobile);
            userSourceEnt.setUserId(userId);
            userSourceEnt.setUserSource(platype.shortValue());
            userSourceEnt.setUserToken(userToken);
            userSourceEnt.setObjIds(openId);
            userSourceEnt.setCreateTime(DateUtils.getTime());
            userSourceEnt.setWxUnionId(wxUnionid);
            userSourceMapper.insertSelective(userSourceEnt);

            /** 注册用户，通知MQ */
//            Map<String, Object> noticeMap = new HashMap<String, Object>();
//            noticeMap.put("id", userId);
//            MQProducerSingleton.getInstance().sendMsg(MQ_TOPIC.USER, MQ_TAG.USER_NEW_REGISTER,
//                    JSONObject.toJSONString(noticeMap));
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        return JsonResult.success(ErrorCode.SUCCESS.getMsg(),map);
    }

    /**
     * 获取微信小程序openId
     *
     * @param appId
     * @param appSecret
     * @param jscode
     * @return
     */
    @Override
    public Map<String,Object> getWechatAppletUserOpenid(String appId, String appSecret, String jscode) {
        // 授权（必填）
        String grant_type = "authorization_code";

        // URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";

        // 请求参数
        String params = "appid=" + appId + "&secret=" + appSecret + "&js_code=" + jscode + "&grant_type=" + grant_type;

        String openId = null;

        String sessionKey = null;

        String unionid = null;

        try {

            // 发送请求
            requestUrl = requestUrl + "?" + params;
            String data = reqGetService.req(requestUrl, null);

            // 解析相应内容（转换成json对象）
            JSONObject json = null;
            if (StringUtils.isNotBlank(data)) {
                json = JSONObject.parseObject(data);
            } else {
                log.error("请求微信获取不到openid，微信返回的结果：" + data);
                return null;
            }

            if (Objects.isNull(json)) {
                return null;
            }

            Object openid = json.get("openid");
            if (Objects.isNull(openid)) {
                log.error("获取不到openid，微信返回的结果：" + data);
                return null;
            }

            openId = String.valueOf(openid);

            Object session_key = json.get("session_key");
            if (!Objects.isNull(session_key)) {
                sessionKey = String.valueOf(session_key);
            }

            Object unionid_id = json.get("unionid");
            if (!Objects.isNull(unionid_id)) {
                unionid = String.valueOf(unionid_id);
            }

        } catch (Exception e) {
            log.error("请求获取微信openid出错", e);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("openId", openId);
        result.put("sessionKey", sessionKey);
        result.put("unionid", unionid);
        return result;
    }


    /**
     *
     * @Description: 获取微信用户绑定的手机号(一键登录)
     * @param @param
     *            getPhoneNumberReq
     * @param @param
     *            outhSourceEnt
     * @param @return
     * @return Result<?>
     * @throws @author
     *             友忠
     * @date 2018年7月30日
     */
    public JsonResult getPhoneNumberByWechat(GetPhoneNumberReq req, OuthSourceEnt outhSourceEnt) {

        Integer platype = outhSourceEnt.getPlatype();

        if (platype == null || platype != 4) {
            return JsonResult.error(ErrorCode.FAILED.getCode(), "平台无权限操作");
        }

        String encryptedData = req.getEncryptedData();

        String sessionKey = req.getSessionKey();

        String iv = req.getIv();

        String encryvi = req.getEncryvi();

        String encryunionid = req.getEncryunionid();

        // 微信临时凭证
        String jscode = req.getJscode();

        String openId = req.getOpenId();

        String wxUnionid = null;

        /** 获取绑定手机号 */
        String phone = WechatUtil.getPhone(openId, encryptedData, sessionKey, iv);
        if (StringUtils.isBlank(phone) && StringUtils.isNoneBlank(jscode)) {

            Map<String, Object> resultMap = appletWxService.getWI(jscode);
            if (resultMap == null || resultMap.isEmpty()) {
                return JsonResult.error(ErrorCode.FAILED.getCode(), "授权失败! 请重新绑定手机号");
            }

            String cuur_openId = resultMap.get("openId") == null ? null : (String) resultMap.get("openId");

            String cuur_sessionKey = resultMap.get("sessionKey") == null ? null : (String) resultMap.get("sessionKey");

            String cuur_unionid = resultMap.get("unionid") == null ? null : (String) resultMap.get("unionid");

            if (StringUtils.isNotBlank(cuur_openId) && !cuur_openId.equals(openId)) {
                log.info("一键登录时，出现获取openid不一致情况---》 openId: " + openId + " <--> cuur_openId: " + cuur_openId);
                openId = cuur_openId;
            }

            if (StringUtils.isNotBlank(cuur_sessionKey)) {
                sessionKey = cuur_sessionKey;
            }

            if (StringUtils.isNotBlank(cuur_unionid)) {
                wxUnionid = cuur_unionid;
            }

            phone = WechatUtil.getPhone(openId, encryptedData, sessionKey, iv);
        }

        if (StringUtils.isBlank(phone)) {
            return JsonResult.error(ErrorCode.FAILED.getCode(), "授权失败! 请重新绑定手机号");
        }

        if (phone.contains("**")) {
            return JsonResult.error(ErrorCode.FAILED.getCode(), "授权失败! 请尝试手动注册");
        }

        /** 获取用户信息 */
        if (StringUtils.isBlank(wxUnionid) && StringUtils.isNotBlank(encryvi) && StringUtils.isNotBlank(encryunionid)) {
            String wbInfo = WechatUtil.decryptData(encryunionid, sessionKey, encryvi);
            if (StringUtils.isNotBlank(wbInfo)) {
                JSONObject json = JSON.parseObject(wbInfo);
                String cuur_openid = json.getString("openId");
                String cuur_unionId = json.getString("unionId");

                if (!cuur_openid.equals(openId)) {
                    log.info("出现用户一键登录openid不一致情况--》 cuur_openid: " + cuur_openid + ",openid: " + openId);
                }
                if (StringUtils.isNotBlank(cuur_unionId)) {
                    wxUnionid = cuur_unionId;
                }
            } else {
                log.info("没有获取到用户关联Id---> phone: " + phone);
            }
        }

        // 若前两次获取unionid失败后，则从缓存中获取一下试下
        if (StringUtils.isBlank(wxUnionid) && StringUtils.isNotBlank(openId) && redisUtils.exists(openId)) {
            wxUnionid = Objects.isNull(redisUtils.get(openId))?"": (String) redisUtils.get(openId);
        }

        // 检查该用户是否存在
        String userId = null;

        String token = null;

        Map<String,Object> userMap = new HashMap<>(1);
        userMap.put("userMobile",phone);
        // 检查该用户是否存在
        EnterpriseUser cuur_userInfoEnt = enterpriseUserMapper.selectByParams(userMap);
        if (!Objects.isNull(cuur_userInfoEnt)) {

            userId = cuur_userInfoEnt.getUserId();

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userMobile", phone);
            params.put("userSource", platype);

            UserSource cuur_userSourceEnt = userSourceMapper.selectByUserMobileAndSource(params);

            // 生成userToken
            String userToken = JwtHelper.createJWT(userId, platype, phone, DomainData.USERTTLMILLIS);
            token = userToken;

            if (Objects.isNull(cuur_userSourceEnt)) {

                // 如果源已存在，则默认更新为最新数据
                params.clear();
                params.put("userId", userId);
                params.put("userSource", platype);
                UserSource userSourceEntBak = userSourceMapper.selectByUserSourceAndUserId(params);
                if (Objects.isNull(userSourceEntBak)) {

                    // 插入用户注册来源数据
                    UserSource userSourceEnt = new UserSource();
                    userSourceEnt.setUserMobile(phone);
                    userSourceEnt.setUserId(userId);
                    userSourceEnt.setUserSource(platype.shortValue());
                    userSourceEnt.setUserToken(userToken);
                    userSourceEnt.setObjIds(openId);
                    userSourceEnt.setCreateTime(DateUtils.getTime());
                    userSourceEnt.setWxUnionId(wxUnionid);
                    userSourceMapper.insertSelective(userSourceEnt);

                } else {
                    userSourceEntBak.setUserToken(userToken);
                    userSourceEntBak.setObjIds(openId);
                    userSourceEntBak.setUserMobile(phone);
                    userSourceEntBak.setWxUnionId(wxUnionid);
                    userSourceEntBak.setUpdateTime(DateUtils.getTime());
                    userSourceMapper.updateByPrimaryKeySelective(userSourceEntBak);
                }

            } else {

                String objIds = cuur_userSourceEnt.getObjIds();
                if (StringUtils.isNoneBlank(objIds) && !objIds.equals(openId)) {
                    cuur_userSourceEnt.setObjIds(openId);
                    log.info(phone + ": 一键绑定注册时, 该手机号已绑定了别的账号, userid- " + userId + " <-objIds->" + objIds
                            + " <-openId->" + openId);
                }

                // 更新token
                cuur_userSourceEnt.setWxUnionId(wxUnionid);
                cuur_userSourceEnt.setUserToken(userToken);
                userSourceMapper.updateByPrimaryKeySelective(cuur_userSourceEnt);
            }

        } else {

            EnterpriseUser userInfoEnt = new EnterpriseUser();
            userInfoEnt.setUserName(phone);
            userInfoEnt.setUserMobile(phone);
            //userInfoEnt.setPlatformType((short) 1); // 这里默认是只有非洗不可平台类型
            userInfoEnt.setUserSource(platype);
            userInfoEnt.setRegDate(DateUtils.getTime());
            userInfoEnt.setUserState(1);
            userInfoEnt.setUserImg("/php/head_photo/default_head_photo.png");
            int result = enterpriseUserMapper.insertSelective(userInfoEnt);
            if (result == 1) {
                userId = userInfoEnt.getUserId();
            }

            // 生成userToken
            String userToken = JwtHelper.createJWT(userId, platype, phone, DomainData.USERTTLMILLIS);
            token = userToken;

            // 插入用户注册来源数据
            UserSource userSourceEnt = new UserSource();
            userSourceEnt.setUserMobile(phone);
            userSourceEnt.setUserId(userId);
            userSourceEnt.setUserSource(platype.shortValue());
            userSourceEnt.setUserToken(userToken);
            userSourceEnt.setObjIds(openId);
            userSourceEnt.setCreateTime(DateUtils.getTime());
            userSourceEnt.setWxUnionId(wxUnionid);
            userSourceMapper.insertSelective(userSourceEnt);

            /** 注册用户，通知MQ */
//            Map<String, Object> noticeMap = new HashMap<String, Object>();
//            noticeMap.put("id", userId);
//            MQProducerSingleton.getInstance().sendMsg(MQ_TOPIC.USER, MQ_TAG.USER_NEW_REGISTER,
//                    com.alibaba.fastjson.JSONObject.toJSONString(noticeMap));
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        return JsonResult.success(ErrorCode.SUCCESS.getMsg(),map);
    }
}
