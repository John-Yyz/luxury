package com.luxury.controller;

import com.luxury.base.BaseController;
import com.luxury.config.URLConstants;
import com.luxury.model.EnterpriseUser;
import com.luxury.request.CheckWechatRegistReq;
import com.luxury.request.OuthSourceEnt;
import com.luxury.service.IEnterpriseUserService;
import com.luxury.utils.Exceptions;
import com.luxury.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 1:43
 */
@Api(description = "【APP】用户信息管理", tags = "EnterpriseUserController", basePath = "/enterpriseUser")
@RestController
@RequestMapping(URLConstants.USER_INFO_CONTROLLER)
@Slf4j
public class EnterpriseUserController extends BaseController {

    @Autowired(required = false)
    IEnterpriseUserService enterpriseUserService;


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


    /**
     * 用户信息（我的）
     * @param enterpriseUser
     * @return
     */
    @PostMapping(value = "getUserInfo")
    @ApiOperation(value = "用户信息（我的）", notes = "用户信息（我的）")
    public JsonResult getUserInfo(@RequestBody EnterpriseUser enterpriseUser){
        return enterpriseUserService.getUserInfo(enterpriseUser);
    }
}
