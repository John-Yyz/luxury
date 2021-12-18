package com.luxury.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.luxury.base.BaseQuery;
import com.luxury.mapper.EnterpriseInfoMapper;
import com.luxury.mapper.EnterpriseUserMapper;
import com.luxury.mapper.UserSourceMapper;
import com.luxury.model.EnterpriseInfo;
import com.luxury.model.EnterpriseUser;
import com.luxury.model.UserSource;
import com.luxury.request.CheckWechatRegistReq;
import com.luxury.request.OuthSourceEnt;
import com.luxury.response.EnterpriseUserResp;
import com.luxury.service.AppletWxService;
import com.luxury.service.IEnterpriseUserService;
import com.luxury.service.ReqGetService;
import com.luxury.service.WechatService;
import com.luxury.utils.ErrorCode;
import com.luxury.utils.JsonResult;
import com.luxury.utils.StringUtils;
import com.luxury.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/25 2:50
 */
@Service(value = "enterpriseUserService")
@Slf4j
public class EnterpriseUserServiceImpl implements IEnterpriseUserService {

    Logger logger = LoggerFactory.getLogger(EnterpriseUserServiceImpl.class);

    @Autowired(required = false)
    EnterpriseUserMapper enterpriseUserMapper;

    @Autowired(required = false)
    EnterpriseInfoMapper enterpriseInfoMapper;

    @Autowired
    WechatService wechatService;

    @Autowired
    AppletWxService appletWxService;

    @Autowired(required = false)
    UserSourceMapper userSourceMapper;

    /**
     * 根据查询条件获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        return null;
    }

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Map<String, Object> info(Integer id) {
        return null;
    }

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        return null;
    }

    /**
     * 检查用户是否存在
     *
     * @param checkWechatRegistReq
     * @return
     */
    @Override
    public JsonResult checkUserIsExist(CheckWechatRegistReq checkWechatRegistReq, OuthSourceEnt outhSourceEnt) {
        if(Objects.isNull(checkWechatRegistReq)){
            logger.info("参数错误");
            return JsonResult.error("参数错误");
        }

        // 请求用户来源（1=微信；2=Android； 3=IOS；4=微信小程序; 5=支付宝小程序；6=抖音小程序；7=其他；100=官方平台；101=代理商平台；102=监控者平台；）
        Integer platype = outhSourceEnt.getPlatype();
        if (null != platype && platype == 0) {
            return JsonResult.error(ErrorCode.FAILED.getCode(), "平台无权限操作");
        }

        if(Objects.isNull(checkWechatRegistReq.getUserSource()) && StringUtils.isBlank(checkWechatRegistReq.getOpenId())){
            logger.info("参数错误");
            return JsonResult.error("参数错误");
        }

        if(checkWechatRegistReq.getUserSource() == 0){
            logger.info("参数错误");
            return JsonResult.error("参数错误");
        }

        String sessionKey = null;
        String unionid = null;
        String openId = null;
        Map<String, Object> result = null;
        switch (platype) {
            case 1:
                result = wechatService.getWxResult(checkWechatRegistReq.getOpenId());
                if (result == null || result.isEmpty()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("openId", checkWechatRegistReq.getOpenId());
                    return JsonResult.error(ErrorCode.USER_NOT_EXIST);
                }

                unionid = result.get("unionid") == null ? null : (String) result.get("unionid");

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                result = appletWxService.getAppletWx(checkWechatRegistReq.getOpenId());
                if (result == null || result.isEmpty()) {
                    return JsonResult.error("获取用户基本信息异常");
                }

                openId = (String) result.get("openId");

                sessionKey = result.get("sessionKey") == null ? null : (String) result.get("sessionKey");

                unionid = result.get("unionid") == null ? null : (String) result.get("unionid");
                break;
            case 5:
                break;
            default:
                return JsonResult.error("无操作权限");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openId", openId);
        params.put("userSource", 1);
        UserSource userSourceEnt = userSourceMapper.findUserIsRegistByOpenId(params);
        if (Objects.isNull(userSourceEnt)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("openId", openId);
            map.put("sessionKey", sessionKey);
            return JsonResult.error(10004,"该用户不存在", map);
        }

        String userId = userSourceEnt.getUserId();
        Map<String,Object> param = new HashMap<>(1);
        param.put("userId",userId);
        EnterpriseUser enterpriseUser = enterpriseUserMapper.selectByParams(param);
        if (Objects.isNull(enterpriseUser)) {
            log.error("既然出现，来源表中有用户，信息表中无数据, 尽快排查 ---》 " + openId);
            return JsonResult.error(ErrorCode.THE_USER_IS_FREEZE.getCode(), "您的账号已被冻结，请联系非洗不可");
        }

        params.clear();
        params.put("userMobile",enterpriseUser.getUserMobile());
        EnterpriseUser upEnterpriseUser = enterpriseUserMapper.selectByParams(params);
        if(Objects.isNull(upEnterpriseUser)){
            //前端调用插入接口
            return JsonResult.error(10001,"用户不存在");
        }else{
            //这个前端要调用修改接口
            return JsonResult.success("用户已存在");
        }
    }

    /**
     * 用戶信息
     *
     * @param enterpriseUser
     * @return
     */
    @Override
    public JsonResult getUserInfo(EnterpriseUser enterpriseUser) {
        if(Objects.isNull(enterpriseUser)){
            return JsonResult.error(ErrorCode.PARAM_ERROR);
        }

        if(StringUtils.isBlank(enterpriseUser.getUserId())){
            return JsonResult.error(ErrorCode.PARAM_ERROR);
        }

        Map<String,Object> params = new HashMap<>(1);
        params.put("userId",enterpriseUser.getUserId());
        enterpriseUser = enterpriseUserMapper.selectByParams(params);
        if(Objects.isNull(enterpriseUser)){
            return JsonResult.error(ErrorCode.USER_NOT_EXIST);
        }
        EnterpriseUserResp enterpriseUserResp = new EnterpriseUserResp();
        BeanUtils.copyProperties(enterpriseUser,enterpriseUserResp);
        EnterpriseInfo enterpriseInfo = enterpriseInfoMapper.selectBySelect(params);
        if(!Objects.isNull(enterpriseInfo)){
            enterpriseUserResp.setAuditStatus(enterpriseInfo.getAuditStatus());
        }
        return JsonResult.success("操作成功",enterpriseUserResp);
    }

    /**
     * 根据实体对象添加记录
     *
     * @param enterpriseUser 实体对象
     * @return
     */
    @Override
    public JsonResult add(EnterpriseUser enterpriseUser) {
        if(Objects.isNull(enterpriseUser)){
            logger.info("参数错误");
            return JsonResult.error("参数错误");
        }
        String code = UUIDUtil.createCode();
        enterpriseUser.setUserId(code);
        int flag = enterpriseUserMapper.insertSelective(enterpriseUser);
        if(flag > 0){
            return JsonResult.success("注册成功",enterpriseUser);
        }
        return JsonResult.error("注册失败");
    }

    /**
     * 根据实体对象更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult update(EnterpriseUser entity) {
        if(Objects.isNull(entity)){
            logger.info("参数错误");
            return JsonResult.error("参数错误");
        }
        int flag = enterpriseUserMapper.updateByPrimaryKeySelective(entity);
        if(flag > 0){
            return JsonResult.success("修改成功",entity);
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 根据实体对象添加、编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(EnterpriseUser entity) {
        return null;
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(EnterpriseUser entity) {
        return null;
    }

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public JsonResult deleteById(Integer id) {
        return null;
    }

    /**
     * 根据ID删除记录
     *
     * @param ids 记录ID
     * @return
     */
    @Override
    public JsonResult deleteByIds(String ids) {
        return null;
    }


}
