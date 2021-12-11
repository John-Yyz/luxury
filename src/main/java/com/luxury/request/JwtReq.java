package com.luxury.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/2 0:36
 */
@ApiModel(value = "com-luxury-request-JwtReq")
@Data
public class JwtReq {

    /**
     * 用户注册来源(1=微信；2=Android； 3=IOS；4=微信小程序; 5=支付宝小程序；6=抖音小程序；7=其他)
     */
    @ApiModelProperty(value = "用户注册来源(1=微信；2=Android； 3=IOS；4=微信小程序; 5=支付宝小程序；6=抖音小程序；7=其他)")
    private Integer userSource;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String userMobile;
}
