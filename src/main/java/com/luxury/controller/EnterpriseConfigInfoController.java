package com.luxury.controller;

import com.luxury.service.IGoodsInfoService;
import com.luxury.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：鉴定配置管理
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 1:43
 */
@Api(description = "【APP】鉴定配置管理", tags = "EnterpriseConfigInfoController", basePath = "/enterpriseConfigInfo")
@RestController
@RequestMapping("/v1/enterpriseConfigInfo")
@Slf4j
public class EnterpriseConfigInfoController {

    /**
     * 获取鉴定费用
     * @param userId 用户ID
     * @param companyId 企业ID
     * @return
     */
    @PostMapping(value = "getAppraisalCost")
    @ApiOperation(value = "获取鉴定费用", notes = "获取鉴定费用")
    public JsonResult getAppraisalCost(@RequestParam String userId,@RequestParam String companyId){
        return JsonResult.success("成功");
    }

}
