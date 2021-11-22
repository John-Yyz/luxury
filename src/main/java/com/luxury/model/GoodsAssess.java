package com.luxury.model;

public class GoodsAssess {
    private String assessId;

    private Integer id;

    private String goodsId;

    private String userId;

    private Integer assessPrice;

    private String applyDate;

    private String assessDate;

    private Integer clinchStatus;

    private Integer clinchPrice;

    private Integer status;

    private String systemUser;

    private String houseKeeper;

    private Integer allType;

    private String area;

    public String getAssessId() {
        return assessId;
    }

    public void setAssessId(String assessId) {
        this.assessId = assessId == null ? null : assessId.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(Integer assessPrice) {
        this.assessPrice = assessPrice;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate == null ? null : applyDate.trim();
    }

    public String getAssessDate() {
        return assessDate;
    }

    public void setAssessDate(String assessDate) {
        this.assessDate = assessDate == null ? null : assessDate.trim();
    }

    public Integer getClinchStatus() {
        return clinchStatus;
    }

    public void setClinchStatus(Integer clinchStatus) {
        this.clinchStatus = clinchStatus;
    }

    public Integer getClinchPrice() {
        return clinchPrice;
    }

    public void setClinchPrice(Integer clinchPrice) {
        this.clinchPrice = clinchPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(String systemUser) {
        this.systemUser = systemUser == null ? null : systemUser.trim();
    }

    public String getHouseKeeper() {
        return houseKeeper;
    }

    public void setHouseKeeper(String houseKeeper) {
        this.houseKeeper = houseKeeper == null ? null : houseKeeper.trim();
    }

    public Integer getAllType() {
        return allType;
    }

    public void setAllType(Integer allType) {
        this.allType = allType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }
}