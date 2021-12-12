package com.luxury.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "com-luxury-model-UserSource")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_source")
public class UserSource {
    /**
     * 自增ID
     */
    @ApiModelProperty(value = "自增ID")
    private Integer id;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id")
    private String userId;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String userMobile;

    /**
     * 用户注册来源(1=微信；2=Android； 3=IOS；4=微信小程序; 5=支付宝小程序；6=抖音小程序；7=其他)
     */
    @ApiModelProperty(value = "用户注册来源(1=微信；2=Android； 3=IOS；4=微信小程序; 5=支付宝小程序；6=抖音小程序；7=其他)")
    private Short userSource;

    /**
     * 其它注册来源id信息
     */
    @ApiModelProperty(value = "其它注册来源id信息")
    private String objIds;

    /**
     * 用户token
     */
    @ApiModelProperty(value = "用户token")
    private String userToken;

    /**
     * 微信unionId
     */
    @ApiModelProperty(value = "微信unionId")
    private String wxUnionId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private String updateTime;

}