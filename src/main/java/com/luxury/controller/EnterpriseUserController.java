package com.luxury.controller;

import com.luxury.model.EnterpriseUser;
import com.luxury.service.IEnterpriseUserService;
import com.luxury.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 1:43
 */
@Api(description = "【APP】用户信息管理", tags = "EnterpriseUserController", basePath = "/enterpriseUser")
@RestController
@RequestMapping("/v1/enterpriseUser")
@Slf4j
public class EnterpriseUserController {

    @Autowired(required = false)
    IEnterpriseUserService enterpriseUserService;

    /**
     * 检查用户是否存在
     * @param enterpriseUser
     * @return
     */
    @PostMapping(value = "checkUserIsExist")
    @ApiOperation(value = "检查用户是否存在", notes = "检查用户是否存在")
    public JsonResult checkUserIsExist(@RequestBody EnterpriseUser enterpriseUser){
        return enterpriseUserService.checkUserIsExist(enterpriseUser);
    }

    /**
     * 注册用户
     * @param enterpriseUser
     * @return
     */
    @PostMapping(value = "regUser")
    @ApiOperation(value = "注册用户", notes = "注册用户")
    public JsonResult regUser(@RequestBody EnterpriseUser enterpriseUser){
        return enterpriseUserService.add(enterpriseUser);
    }


    /**
     * 修改用户
     * @param enterpriseUser
     * @return
     */
    @PostMapping(value = "updateUser")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public JsonResult updateUser(@RequestBody EnterpriseUser enterpriseUser){
        return enterpriseUserService.update(enterpriseUser);
    }
}
