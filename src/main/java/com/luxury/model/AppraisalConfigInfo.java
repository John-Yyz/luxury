package com.luxury.model;

public class AppraisalConfigInfo {
    private Integer id;

    private Integer tollType;

    private Double chargePrice;

    private Integer free;

    private Integer objectType;

    private String userId;

    private String systemUser;

    private Integer status;

    private String createDate;

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
        this.userId = userId == null ? null : userId.trim();
    }

    public String getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(String systemUser) {
        this.systemUser = systemUser == null ? null : systemUser.trim();
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
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }
}