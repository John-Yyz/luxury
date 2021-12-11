package com.luxury.controller;

import com.luxury.model.EnterpriseInfo;
import com.luxury.model.EnterpriseUser;
import com.luxury.service.IEnterpriseInfoService;
import com.luxury.service.IEnterpriseUserService;
import com.luxury.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 1:43
 */
@Api(description = "【APP】企业认证信息管理", tags = "EnterpriseInfoController", basePath = "/enterpriseInfo")
@RestController
@RequestMapping("/v1/enterpriseInfo")
@Slf4j
public class EnterpriseInfoController {

    @Autowired(required = false)
    IEnterpriseInfoService enterpriseInfoService;

    /**
     * 检查用户是否存在
     * @param userId
     * @return
     */
    @PostMapping(value = "checkIsEnterprise")
    @ApiOperation(value = "检查用户是否存在", notes = "检查用户是否存在")
    public JsonResult checkIsEnterprise(@RequestParam String userId){
        return enterpriseInfoService.checkIsEnterprise(userId);
    }

    /**
     * 注册用户
     * @param enterpriseInfo
     * @return
     */
    @PostMapping(value = "addEnterpriseInfo")
    @ApiOperation(value = "添加企业认证信息", notes = "添加企业认证信息")
    public JsonResult addEnterpriseInfo(@RequestBody EnterpriseInfo enterpriseInfo){
        return enterpriseInfoService.add(enterpriseInfo);
    }


    /**
     * 修改用户
     * @param EnterpriseInfo
     * @return
     */
    @PostMapping(value = "updateEnterpriseInfo")
    @ApiOperation(value = "修改企业认证信息", notes = "修改企业认证信息")
    public JsonResult updateEnterpriseInfo(@RequestBody EnterpriseInfo EnterpriseInfo){
        return enterpriseInfoService.update(EnterpriseInfo);
    }
}
