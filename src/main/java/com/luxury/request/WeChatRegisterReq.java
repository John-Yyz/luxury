package com.luxury.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/16 13:00
 */
@Data
public class WeChatRegisterReq implements Serializable {

    //手机号码
    @NotBlank(message="用户手机不能为空")
    private String userMobile;

    //验证码
    @NotBlank(message="手机验证码不能为空")
    private String sendCode;

    //微信openid
    private String openId;

    //小程序私钥
    private String sessionKey;

    //小程序-加密数据
    private String encryunionid;

    //小程序-加密算法的初始向量
    private String encryvi;

    //jscode
    private String jscode;
}
