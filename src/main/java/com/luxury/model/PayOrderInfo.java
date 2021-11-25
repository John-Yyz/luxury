package com.luxury.model;

public class PayOrderInfo {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 记录编号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 企业
     */
    private String enterpriseId;

    /**
     * 状态（1=待支付；2=已支付；3=已失效；4=已退款）
     */
    private Integer orderStatus;

    /**
     * 交易编号
     */
    private String tradeNo;

    /**
     * 订单金额
     */
    private Integer orderMoney;

    /**
     * 退款金额
     */
    private Integer refundMoney;

    /**
     * 支付时间
     */
    private String orderDate;

    /**
     * 退款时间
     */
    private String refundDate;

    /**
     * 订单来源（1=App；2=微信；3=支付宝）
     */
    private Integer orderSource;

    /**
     * 支付渠道（1=微信；2=支付宝）
     */
    private Integer orderPayType;

    /**
     * 订单描述
     */
    private String orderDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Integer orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Integer refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public Integer getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(Integer orderPayType) {
        this.orderPayType = orderPayType;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}