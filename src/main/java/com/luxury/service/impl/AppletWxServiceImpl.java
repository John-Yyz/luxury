package com.luxury.service.impl;

import com.luxury.config.DomainData;
import com.luxury.mapper.UserSourceMapper;
import com.luxury.model.UserSource;
import com.luxury.service.AppletWxService;
import com.luxury.service.IEnterpriseUserService;
import com.luxury.service.IUserInfoRegistService;
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
 * @date 2021/12/2 1:46
 */
@Service(value = "appletWxService")
@Slf4j
public class AppletWxServiceImpl implements AppletWxService {

    @Autowired
    IEnterpriseUserService enterpriseUserService;

    @Autowired
    UserSourceMapper userSourceMapper;

    @Autowired
    IUserInfoRegistService userInfoRegistService;

    /**
     * 通过jscode获取微信小程序信息
     *
     * @param jscode
     * @return
     */
    @Override
    public Map<String, Object> getAppletWx(String jscode) {
        String appId = DomainData.appid;
        String appSecret = DomainData.appSecret;

        // 请求微信小程序获取openId
        Map<String, Object> result = userInfoRegistService.getWechatAppletUserOpenid(appId, appSecret, jscode);
        if (result == null || result.isEmpty())
            return null;

        String openId = result.get("openId") == null ? "" : (String) result.get("openId");
        if (StringUtils.isBlank(openId))
            return null;

        String sessionKey = result.get("sessionKey") == null ? "" : (String) result.get("sessionKey");

        String unionid = result.get("unionid") == null ? "" : (String) result.get("unionid");

        // 1、判断该用户是否小程序注册过
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openId", openId);
        params.put("userSource", 4);
        UserSource userSource = userSourceMapper.findUserIsRegistByOpenId(params);
        if(Objects.isNull(userSource)){
            // 2、根据 unionid 判断 用户是否采用公众号注册过
            if (StringUtils.isBlank(unionid)) {
                result.put("unionid", unionid);
                result.put("openId", openId);
                result.put("sessionKey", sessionKey);
                return result;
            }

            // 3、公众号，是否注册过
            params.clear();
            params.put("userSource", 1);
            params.put("unionid", unionid);
            userSource = userSourceMapper.findUserIsRegistByUnionid(params);
            if(Objects.isNull(userSource)){
                result.put("unionid", unionid);
                result.put("openId", openId);
                result.put("sessionKey", sessionKey);
                return result;
            }

            String userMobile = userSource.getUserMobile();
            params.clear();
            params.put("userMobile", userMobile);
            params.put("userSource", 4);
            UserSource exist_userSource = userSourceMapper.selectByUserMobileAndSource(params);
            if(Objects.isNull(exist_userSource)){
                // 4、若公众号注册过； 默认该用户小程序注册; 插入用户注册来源数据
                UserSource userSourceEnt = new UserSource();
                userSourceEnt.setUserMobile(userSource.getUserMobile());
                userSourceEnt.setUserId(userSource.getUserId());
                userSourceEnt.setUserSource((short) 4);
                userSourceEnt.setUserToken(null);
                userSourceEnt.setObjIds(openId);
                userSourceEnt.setCreateTime(DateUtils.getTime());
                userSourceEnt.setWxUnionId(unionid);
                userSourceMapper.insertSelective(userSourceEnt);
            }else{
                String objIds = exist_userSource.getObjIds();
                if (StringUtils.isNoneBlank(objIds) && !objIds.equals(openId)) {
                    exist_userSource.setObjIds(openId);
                    log.info(userMobile + ": 检查注册时, 该手机号已绑定了别的账号, userid- " + exist_userSource.getUserId()
                            + " <-objIds->" + objIds + " <-openId->" + openId);
                }

                // 更新token
                exist_userSource.setWxUnionId(unionid);
                userSourceMapper.updateByPrimaryKey(exist_userSource);
            }

        }else{
            if (StringUtils.isNotBlank(unionid)) {
                userSource.setWxUnionId(unionid);
                userSourceMapper.updateByPrimaryKey(userSource);
            }
        }

        result.put("openId", openId);
        result.put("sessionKey", sessionKey);
        result.put("unionid", unionid);
        return result;
    }


    public Map<String, Object> getWI(String jscode) {

        String appId = DomainData.appid;

        String appSecret = DomainData.appSecret;

        // 请求微信小程序获取openId
        Map<String, Object> result = userInfoRegistService.getWechatAppletUserOpenid(appId, appSecret, jscode);
        if (result == null || result.isEmpty())
            return null;

        String openId = result.get("openId") == null ? "" : (String) result.get("openId");
        if (StringUtils.isBlank(openId))
            return null;

        String sessionKey = result.get("sessionKey") == null ? "" : (String) result.get("sessionKey");

        String unionid = result.get("unionid") == null ? "" : (String) result.get("unionid");

        result.put("openId", openId);
        result.put("sessionKey", sessionKey);
        result.put("unionid", unionid);
        return result;

    }
}
