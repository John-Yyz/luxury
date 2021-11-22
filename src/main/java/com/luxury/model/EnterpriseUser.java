package com.luxury.model;

public class EnterpriseUser {
    private String userId;

    private Integer id;

    private String userName;

    private String nickName;

    private String userMobile;

    private String userImg;

    private String weixinOpenid;

    private String userPwd;

    private Integer userSource;

    private String regDate;

    private Integer userState;

    private Integer enterpriseType;

    private String smallOpenid;

    private String aliOpenid;

    private String tiktokOpenid;

    private String unionid;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg == null ? null : userImg.trim();
    }

    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid == null ? null : weixinOpenid.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public Integer getUserSource() {
        return userSource;
    }

    public void setUserSource(Integer userSource) {
        this.userSource = userSource;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate == null ? null : regDate.trim();
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Integer getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(Integer enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getSmallOpenid() {
        return smallOpenid;
    }

    public void setSmallOpenid(String smallOpenid) {
        this.smallOpenid = smallOpenid == null ? null : smallOpenid.trim();
    }

    public String getAliOpenid() {
        return aliOpenid;
    }

    public void setAliOpenid(String aliOpenid) {
        this.aliOpenid = aliOpenid == null ? null : aliOpenid.trim();
    }

    public String getTiktokOpenid() {
        return tiktokOpenid;
    }

    public void setTiktokOpenid(String tiktokOpenid) {
        this.tiktokOpenid = tiktokOpenid == null ? null : tiktokOpenid.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }
}