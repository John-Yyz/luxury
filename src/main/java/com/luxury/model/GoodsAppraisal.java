package com.luxury.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "com-luxury-model-GoodsAppraisal")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods_appraisal")
public class GoodsAppraisal {
    /**
     * 自增ID
     */
    @ApiModelProperty(value = "自增ID")
    private Integer id;

    /**
     * 鉴定记录编号
     */
    @ApiModelProperty(value = "鉴定记录编号")
    private String appraisalId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private String goodsId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 鉴定申请时间
     */
    @ApiModelProperty(value = "鉴定申请时间")
    private String applyDate;

    /**
     * 鉴定时间
     */
    @ApiModelProperty(value = "鉴定时间")
    private String appraisalDate;

    /**
     * 是否正品(0=等待更新；1=正品；2=赝品)
     */
    @ApiModelProperty(value = "是否正品(0=等待更新；1=正品；2=赝品)")
    private Integer authenticType;

    /**
     * 是否已有证书
     */
    @ApiModelProperty(value = "是否已有证书")
    private Integer haveFlag;

    /**
     * 证书
     */
    @ApiModelProperty(value = "证书")
    private String certificate;

    /**
     * 鉴定价格（单位：分）
     */
    @ApiModelProperty(value = "鉴定价格（单位：分）")
    private Integer appraisalPrice;

    /**
     * 优惠价格（单位：分）
     */
    @ApiModelProperty(value = "优惠价格（单位：分）")
    private Integer discount;

    /**
     * 是否成交
     */
    @ApiModelProperty(value = "是否成交")
    private Integer clinchType;

    /**
     * 成交价格（单位：分）
     */
    @ApiModelProperty(value = "成交价格（单位：分）")
    private Integer clinchPrice;

    /**
     * 鉴定结果（0=信息需要更新；1=正品；2=赝品；）
     */
    @ApiModelProperty(value = "鉴定结果（0=信息需要更新；1=正品；2=赝品；）")
    private Integer status;

    /**
     * 鉴定/估价师
     */
    @ApiModelProperty(value = "鉴定/估价师")
    private String systemUser;

    /**
     * 管家
     */
    @ApiModelProperty(value = "管家")
    private String houseKeeper;

    /**
     * 是否分配给所有鉴定师（0=否；1=所有）
     */
    @ApiModelProperty(value = "是否分配给所有鉴定师（0=否；1=所有）")
    private Integer allType;

    /**
     * 区域
     */
    @ApiModelProperty(value = "区域")
    private String area;
}