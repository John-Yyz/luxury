package com.luxury.model;

public class GoodsInfo {
    private String 商品id;

    private Integer id;

    private String goodsName;

    private Integer goodsType;

    private String pictureUrl;

    private Integer status;

    public String get商品id() {
        return 商品id;
    }

    public void set商品id(String 商品id) {
        this.商品id = 商品id == null ? null : 商品id.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}