package com.luxury.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.luxury.config.DomainData;
import com.luxury.config.SecurityCont;
import com.luxury.mapper.UserSourceMapper;
import com.luxury.model.UserSource;
import com.luxury.service.ReqGetService;
import com.luxury.service.ReqPostService;
import com.luxury.service.WechatService;
import com.luxury.utils.DateUtils;
import com.luxury.utils.StringUtils;
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
 * @date 2021/12/3 0:47
 */
@Service(value = "wechatService")
@Slf4j
public class WechatServiceImpl implements WechatService {

    @Autowired(required = false)
    UserSourceMapper userSourceMapper;
    @Autowired
    ReqGetService reqGetService;
    @Autowired
    ReqPostService reqPostService;

    @Override
    public Map<String, Object> getWxResult(String openId) {
        Map<String, Object> result = new HashMap<String, Object>();
        // 1、获取用户unionid
        String unionid = getUnionid(openId);
        // 1、判断该用户是否公众号注册过
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openId", openId);
        params.put("userSource", 1);
        UserSource userSource = userSourceMapper.findUserIsRegistByOpenId(params);
        if (Objects.isNull(userSource)) {

            if (StringUtils.isBlank(unionid)) {
                return null;
            }

            // 2、微信小程序是否注册过
            params.clear();
            params.put("userSource", 4);
            params.put("unionid", unionid);
            userSource = userSourceMapper.findUserIsRegistByUnionid(params);
            if (Objects.isNull(userSource)) {
                result.put("unionid", unionid);
                result.put("openId", openId);
                result.put("sessionKey", null);
                return result;
            }

            // 3、若小程序注册过； 默认该用户公众号注册; 插入用户注册来源数据
            UserSource userSourceEnt = new UserSource();
            userSourceEnt.setUserMobile(userSource.getUserMobile());
            userSourceEnt.setUserId(userSource.getUserId());
            userSourceEnt.setUserSource((short) 1);
            userSourceEnt.setUserToken(null);
            userSourceEnt.setObjIds(openId);
            userSourceEnt.setCreateTime(DateUtils.getTime());
            userSourceEnt.setWxUnionId(unionid);
            userSourceMapper.insertSelective(userSourceEnt);
        } else {
            if (StringUtils.isNotBlank(unionid)) {
                userSource.setWxUnionId(unionid);
                userSourceMapper.updateByPrimaryKey(userSource);
            }
        }

        return null;
    }


    public String getUnionid(String openId) {

        String access_token = getAccessToken();
        if (StringUtils.isBlank(access_token))
            return null;

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("https://api.weixin.qq.com/cgi-bin/user/info");
        sqlBuilder.append("?access_token=" + access_token);
        sqlBuilder.append("&openid=" + openId);
        sqlBuilder.append("&lang=zh_CN");
        String requestUrl = sqlBuilder.toString();

        String resp = reqGetService.req(requestUrl, null);
        if (StringUtils.isBlank(resp)) {
            log.error("获取用户unionid失败--》 " + openId);
            return null;
        }

        JSONObject jsonObject = JSONObject.parseObject(resp);
        Integer subscribe = jsonObject.getInteger("subscribe");
        if (subscribe == null) {
            log.info("获取unionid异常---》 " + openId);
            return null;
        }

        if (subscribe == 0) {
            log.info("用户没有关注公众号信息，获取不到unionid--》 " + resp);
            return null;
        }

        String unionid = jsonObject.getString("unionid");

        return unionid;
    }

    public String getAccessToken() {
        //String access_token = getWxAccessToken();
        String access_token = null;
        if(StringUtils.isNotBlank(access_token)){
            return access_token;
        }

        String appid = DomainData.isOffcialEnv == 0 ? SecurityCont.TEST_WX_WECHAT_APPID : SecurityCont.WX_WECHAT_APPID;

        String appsecret = DomainData.isOffcialEnv == 0 ? SecurityCont.TEST_WX_WECHAT_APP_SECRET
                : SecurityCont.WX_WECHAT_APP_SECRET;

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("https://api.weixin.qq.com/cgi-bin/token");
        sqlBuilder.append("?grant_type=client_credential");
        sqlBuilder.append("&appid=" + appid);
        sqlBuilder.append("&secret=" + appsecret);
        String requestUrl = sqlBuilder.toString();

        String ascc = reqGetService.req(requestUrl, null);
        if (StringUtils.isBlank(ascc)) {
            log.error("访问公众号Token失败---》 " + ascc);
            return null;
        }

        JSONObject jsonObject = JSONObject.parseObject(ascc);
        access_token = jsonObject.getString("access_token");
        if (StringUtils.isBlank(access_token)) {
            log.error("访问Token失效---》 " + ascc);
            return null;
        }

        return access_token;
    }

    public String getWxAccessToken() {
        String access_token = null;
        String getAcctoken_url = SecurityCont.acctoken_url;

        Map<String, Object> parm_map = new HashMap<String, Object>();
        parm_map.put("user_name", SecurityCont.user_name);
        parm_map.put("password", SecurityCont.password);
        String resp = reqPostService.reqByForm(getAcctoken_url, parm_map);
        if (StringUtils.isBlank(resp)) {
            log.error("访问公众号Token失败---》 " + resp);
            return access_token;
        }


        JSONObject jsonObject = JSONObject.parseObject(resp);
        int status = jsonObject.getIntValue("status");
        if (status == 0) {
            access_token = jsonObject.getString("token");
        }

        if (StringUtils.isBlank(access_token)) {
            log.error("访问Token失效---》 " + resp);
            return access_token;
        }

        return access_token;
    }
}
