package com.luxury.request;

import java.io.Serializable;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/4 2:17
 */
public class OuthSourceEnt {

    private String uid; //访问者id

    private Integer platype;   //操作来源(1=微信；2=Android； 3=IOS；4=微信小程序; 5=支付宝小程序；6=抖音小程序；7=其他；100=官方平台；101=代理商平台；102=监控者平台；)

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getPlatype() {
        return platype;
    }

    public void setPlatype(Integer platype) {
        this.platype = platype;
    }
}
