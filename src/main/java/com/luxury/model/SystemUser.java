package com.luxury.model;

public class SystemUser {
    /**
     * 管理用户ID
     */
    private String manageUserId;

    /**
     * 自增id
     */
    private Integer id;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户名（昵称）
     */
    private String userName;

    /**
     * 账号名
     */
    private String userAccount;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 性别
     */
    private Integer userSex;

    /**
     * 角色类型（1=管理员，2=管家，3=估价师，4=鉴定师）
     */
    private Integer roleType;

    /**
     * 状态（1=冻结；2=正常；3=已删除）
     */
    private Integer status;

    /**
     * openID
     */
    private String openid;

    /**
     * 区域
     */
    private String area;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 更新时间
     */
    private String updateDate;

    /**
     * 角色
     */
    private String roleId;

    public String getManageUserId() {
        return manageUserId;
    }

    public void setManageUserId(String manageUserId) {
        this.manageUserId = manageUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}