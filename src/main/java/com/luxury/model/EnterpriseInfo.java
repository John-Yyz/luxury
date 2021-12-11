package com.luxury.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "com-luxury-model-EnterpriseInfo")
@Data
@Builder
@TableName(value = "enterprise_info")
public class EnterpriseInfo {
    /**
     * 企业编号
     */
    @ApiModelProperty(value = "企业编号")
    private String enterpriseId;

    /**
     * id
     */
    @ApiModelProperty(value = "自增id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;

    /**
     * 企业人名字(真实姓名)
     */
    @ApiModelProperty(value = "企业人名字(真实姓名)")
    private String userName;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    /**
     * 执照编号
     */
    @ApiModelProperty(value = "执照编号")
    private String licenseNumber;

    /**
     * 营业执照
     */
    @ApiModelProperty(value = "营业执照")
    private String businessLicense;

    /**
     * 认证时间
     */
    @ApiModelProperty(value = "认证时间")
    private String createDate;

    /**
     * 启用状态（1=启用；2=禁用）
     */
    @ApiModelProperty(value = "启用状态（1=启用；2=禁用）")
    private Integer status;

    /**
     * 审核状态（1=审核通过；2=审核不通过）
     */
    @ApiModelProperty(value = "审核状态（1=审核通过；2=审核不通过）")
    private Integer auditStatus;

    /**
     * 审核原因
     */
    @ApiModelProperty(value = "审核原因")
    private String auditReason;
}