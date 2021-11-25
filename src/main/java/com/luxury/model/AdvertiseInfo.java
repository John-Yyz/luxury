package com.luxury.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "com-luxury-model-AdvertiseInfo")
@Data
@Builder
@TableName(value = "advertise_info")
public class AdvertiseInfo {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 广告名称
     */
    @ApiModelProperty(value = "展示位的名称")
    private String name;

    /**
     * 内容类型（1=图片；2=链接）
     */
    @ApiModelProperty(value = "展示位的名称")
    private Integer contentType;

    /**
     * 内容
     */
    @ApiModelProperty(value = "展示位的名称")
    private String content;

    /**
     * 是否有链接地址
     */
    @ApiModelProperty(value = "展示位的名称")
    private Integer linkType;

    /**
     * 链接地址
     */
    @ApiModelProperty(value = "展示位的名称")
    private String linkUrl;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "展示位的名称")
    private String pushDate;

    /**
     * 优先级
     */
    @ApiModelProperty(value = "展示位的名称")
    private Integer sort;

    /**
     * 状态（1=有效；2=无效）
     */
    @ApiModelProperty(value = "展示位的名称")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "展示位的名称")
    private String createDate;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "展示位的名称")
    private String updateDate;

    /**
     * 创建人（发布人）
     */
    @ApiModelProperty(value = "展示位的名称")
    private String createId;

}