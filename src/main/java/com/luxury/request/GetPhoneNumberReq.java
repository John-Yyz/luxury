package com.luxury.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/18 16:19
 */
@ApiModel(value = "com-luxury-request-GetPhoneNumberReq")
public class GetPhoneNumberReq implements Serializable {

    /**
     * 加密数据
     */
    @NotBlank(message="授权数据必填")
    @ApiModelProperty(value = "加密数据")
    private String encryptedData;

    //小程序私钥
    @NotBlank(message="授权数据必填")
    @ApiModelProperty(value = "小程序私钥")
    private String sessionKey;

    //加密常量
    @NotBlank(message="授权数据必填")
    @ApiModelProperty(value = "加密常量")
    private String iv;

    //微信openid
    @NotBlank(message="授权数据必填")
    @ApiModelProperty(value = "微信openid")
    private String openId;


    //小程序-加密数据
    @ApiModelProperty(value = "小程序-加密数据")
    private String encryunionid;

    //小程序-加密算法的初始向量
    @ApiModelProperty(value = "小程序-加密算法的初始向量")
    private String encryvi;

    //微信临时凭证
    @ApiModelProperty(value = "微信临时凭证")
    private String jscode;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getEncryunionid() {
        return encryunionid;
    }

    public void setEncryunionid(String encryunionid) {
        this.encryunionid = encryunionid;
    }

    public String getEncryvi() {
        return encryvi;
    }

    public void setEncryvi(String encryvi) {
        this.encryvi = encryvi;
    }

    public String getJscode() {
        return jscode;
    }

    public void setJscode(String jscode) {
        this.jscode = jscode;
    }
}
