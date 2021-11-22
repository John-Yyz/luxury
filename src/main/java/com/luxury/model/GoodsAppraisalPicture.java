package com.luxury.model;

public class GoodsAppraisalPicture {
    private Integer id;

    private String assessId;

    private Integer assessType;

    private Integer type;

    private String imgUrl;

    private Integer imgStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssessId() {
        return assessId;
    }

    public void setAssessId(String assessId) {
        this.assessId = assessId == null ? null : assessId.trim();
    }

    public Integer getAssessType() {
        return assessType;
    }

    public void setAssessType(Integer assessType) {
        this.assessType = assessType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(Integer imgStatus) {
        this.imgStatus = imgStatus;
    }
}