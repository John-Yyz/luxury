package com.luxury.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(value = "com-luxury-model-EnterpriseUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "enterprise_user")
public class EnterpriseUser implements Serializable {
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String userId;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String userMobile;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String userImg;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid")
    private String weixinOpenid;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String userPwd;

    /**
     * 用户注册来源(1=微信；2=Android； 3=IOS；4=微信小程序; 5=支付宝小程序；6=抖音小程序；7=其他)
     */
    @ApiModelProperty(value = "用户注册来源(1=微信；2=Android； 3=IOS；4=微信小程序; 5=支付宝小程序；6=抖音小程序；7=其他)")
    private Integer userSource;

    /**
     * 用户注册时间
     */
    @ApiModelProperty(value = "用户注册时间")
    private String regDate;

    /**
     * 用户状态(1=正常；2=冻结；3=已删除)
     */
    @ApiModelProperty(value = "用户状态(1=正常；2=冻结；3=已删除)")
    private Integer userState;

    /**
     * 是否企业用户
     */
    @ApiModelProperty(value = "是否企业用户")
    private Integer enterpriseType;

    /**
     * 微信小程序openid
     */
    @ApiModelProperty(value = "微信小程序openid")
    private String smallOpenid;

    /**
     * 支付宝小程序openid
     */
    @ApiModelProperty(value = "支付宝小程序openid")
    private String aliOpenid;

    /**
     * 抖音小程序openid
     */
    @ApiModelProperty(value = "抖音小程序openid")
    private String tiktokOpenid;

    /**
     * 小程序unionID
     */
    @ApiModelProperty(value = "小程序unionID")
    private String unionid;
}