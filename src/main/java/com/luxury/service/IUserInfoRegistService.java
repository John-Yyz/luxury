package com.luxury.service;

import com.luxury.request.GetPhoneNumberReq;
import com.luxury.request.OuthSourceEnt;
import com.luxury.request.WeChatRegisterReq;
import com.luxury.utils.JsonResult;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/17 1:08
 */
public interface IUserInfoRegistService {

    JsonResult weChatRegister(WeChatRegisterReq req, OuthSourceEnt outhSourceEnt);


    /**
     * 获取微信小程序openId
     * @param appId
     * @param appSecret
     * @param jscode
     * @return
     */
    Map<String,Object> getWechatAppletUserOpenid(String appId, String appSecret, String jscode);

    /**
     * 获取微信用户绑定的手机号(一键登录)
     * @return
     */
    JsonResult getPhoneNumberByWechat(GetPhoneNumberReq req, OuthSourceEnt outhSourceEnt);
}
