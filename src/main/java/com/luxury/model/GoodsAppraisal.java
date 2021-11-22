package com.luxury.model;

public class GoodsAppraisal {
    private Integer id;

    private String appraisalId;

    private String goodsId;

    private String userId;

    private String applyDate;

    private String appraisalDate;

    private Integer authenticType;

    private Integer haveFlag;

    private String certificate;

    private Integer appraisalPrice;

    private Integer discount;

    private Integer clinchType;

    private Integer clinchPrice;

    private Integer status;

    private String systemUser;

    private String houseKeeper;

    private Integer allType;

    private String area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppraisalId() {
        return appraisalId;
    }

    public void setAppraisalId(String appraisalId) {
        this.appraisalId = appraisalId == null ? null : appraisalId.trim();
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

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate == null ? null : applyDate.trim();
    }

    public String getAppraisalDate() {
        return appraisalDate;
    }

    public void setAppraisalDate(String appraisalDate) {
        this.appraisalDate = appraisalDate == null ? null : appraisalDate.trim();
    }

    public Integer getAuthenticType() {
        return authenticType;
    }

    public void setAuthenticType(Integer authenticType) {
        this.authenticType = authenticType;
    }

    public Integer getHaveFlag() {
        return haveFlag;
    }

    public void setHaveFlag(Integer haveFlag) {
        this.haveFlag = haveFlag;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }

    public Integer getAppraisalPrice() {
        return appraisalPrice;
    }

    public void setAppraisalPrice(Integer appraisalPrice) {
        this.appraisalPrice = appraisalPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getClinchType() {
        return clinchType;
    }

    public void setClinchType(Integer clinchType) {
        this.clinchType = clinchType;
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