package com.luxury.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "com-luxury-model-GoodsInfo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods_info")
public class GoodsInfo {
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品编号")
    private String goodsId;

    /**
     * id
     */
    @ApiModelProperty(value = "自增ID")
    private Integer id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品类别(1=名包；2=名表；3=首饰；99=其他)
     */
    @ApiModelProperty(value = "商品类别(1=名包；2=名表；3=首饰；99=其他)")
    private Integer goodsType;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    private String pictureUrl;

    /**
     * 状态（1=启用；2=禁用）
     */
    @ApiModelProperty(value = "状态（1=启用；2=禁用）")
    private Integer status;
}