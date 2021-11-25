package com.luxury.model;

public class GoodsAssess {
    /**
     * 估价记录编号
     */
    private String assessId;

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 估价价格（单位：分）
     */
    private Integer assessPrice;

    /**
     * 申请时间
     */
    private String applyDate;

    /**
     * 估价时间
     */
    private String assessDate;

    /**
     * 是否成交（1=是；2=否）
     */
    private Integer clinchStatus;

    /**
     * 成交价（单位：分）
     */
    private Integer clinchPrice;

    /**
     * 状态（0=待估价；1=已估价）
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
     * 是否分配给所有估价师（0=否；1=所有）
     */
    private Integer allType;

    /**
     * 区域
     */
    private String area;

    public String getAssessId() {
        return assessId;
    }

    public void setAssessId(String assessId) {
        this.assessId = assessId;
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
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        this.applyDate = applyDate;
    }

    public String getAssessDate() {
        return assessDate;
    }

    public void setAssessDate(String assessDate) {
        this.assessDate = assessDate;
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