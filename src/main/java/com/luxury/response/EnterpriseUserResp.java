package com.luxury.response;

import com.luxury.model.EnterpriseUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/28 20:39
 */
@ApiModel(value = "com-luxury-response-EnterpriseUserResp")
@Data
public class EnterpriseUserResp extends EnterpriseUser implements Serializable {

    /**
     * 企业认证状态（0=待审核；1=审核通过；2=审核不通过）
     */
    @ApiModelProperty(value = "企业认证状态")
    private Integer auditStatus;
}
