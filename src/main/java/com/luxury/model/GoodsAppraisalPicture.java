package com.luxury.model;

public class GoodsAppraisalPicture {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 估价/鉴定记录编号
     */
    private String assessId;

    /**
     * 类型（1=估价；2=鉴定）
     */
    private Integer assessType;

    /**
     * 图片类型（1=完整图；2=正面图；3=反面图；4=底部图；5=内标图；6=数字编码；7=五金图；8=logo；9=拉链图；10=其他（更多））
     */
    private Integer type;

    /**
     * 图片
     */
    private String imgUrl;

    /**
     * 图片状态(1=正常；2=不清晰)
     */
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
        this.assessId = assessId;
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
        this.imgUrl = imgUrl;
    }

    public Integer getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(Integer imgStatus) {
        this.imgStatus = imgStatus;
    }
}