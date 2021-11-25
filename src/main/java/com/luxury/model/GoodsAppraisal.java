package com.luxury.model;

public class GoodsAppraisal {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 鉴定记录编号
     */
    private String appraisalId;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 鉴定申请时间
     */
    private String applyDate;

    /**
     * 鉴定时间
     */
    private String appraisalDate;

    /**
     * 是否正品(0=等待更新；1=正品；2=赝品)
     */
    private Integer authenticType;

    /**
     * 是否已有证书
     */
    private Integer haveFlag;

    /**
     * 证书
     */
    private String certificate;

    /**
     * 鉴定价格（单位：分）
     */
    private Integer appraisalPrice;

    /**
     * 优惠价格（单位：分）
     */
    private Integer discount;

    /**
     * 是否成交
     */
    private Integer clinchType;

    /**
     * 成交价格（单位：分）
     */
    private Integer clinchPrice;

    /**
     * 鉴定结果（0=信息需要更新；1=正品；2=赝品；）
     */
    private Integer status;

    /**
     * 鉴定/估价师
     */
    private String systemUser;

    /**
     * 管家
     */
    private String houseKeeper;

    /**
     * 是否分配给所有鉴定师（0=否；1=所有）
     */
    private Integer allType;

    /**
     * 区域
     */
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
        this.appraisalId = appraisalId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getAppraisalDate() {
        return appraisalDate;
    }

    public void setAppraisalDate(String appraisalDate) {
        this.appraisalDate = appraisalDate;
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
        this.certificate = certificate;
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
        this.systemUser = systemUser;
    }

    public String getHouseKeeper() {
        return houseKeeper;
    }

    public void setHouseKeeper(String houseKeeper) {
        this.houseKeeper = houseKeeper;
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
        this.area = area;
    }
}