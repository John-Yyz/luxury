package com.luxury.model;

public class AppraisalConfigInfo {
    /**
     * id
     */
    private Integer id;

    /**
     * 收费类型（1=按次；2=统一）
     */
    private Integer tollType;

    /**
     * 费用
     */
    private Double chargePrice;

    /**
     * 免费次数
     */
    private Integer free;

    /**
     * 作用对象（1=指定用户；2=指导企业；3=所有）
     */
    private Integer objectType;

    /**
     * 用户
     */
    private String userId;

    /**
     * 企业
     */
    private String systemUser;

    /**
     * 状态（0=启用；1=禁用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 修改时间
     */
    private String updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTollType() {
        return tollType;
    }

    public void setTollType(Integer tollType) {
        this.tollType = tollType;
    }

    public Double getChargePrice() {
        return chargePrice;
    }

    public void setChargePrice(Double chargePrice) {
        this.chargePrice = chargePrice;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(String systemUser) {
        this.systemUser = systemUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}