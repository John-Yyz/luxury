package com.luxury.controller;

import com.luxury.base.BaseController;
import com.luxury.config.URLConstants;
import com.luxury.model.EnterpriseUser;
import com.luxury.request.CheckWechatRegistReq;
import com.luxury.request.HttpReqBodyBasic;
import com.luxury.request.OuthSourceEnt;
import com.luxury.request.WeChatRegisterReq;
import com.luxury.service.IEnterpriseUserService;
import com.luxury.utils.Exceptions;
import com.luxury.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 1:43
 */
@Api(description = "【APP】用户信息管理", tags = "RegistController", basePath = "/registController")
@RestController
@RequestMapping(URLConstants.REGIST_CONTROLLER)
@Slf4j
public class RegistController extends BaseController {

    @Autowired
    IEnterpriseUserService enterpriseUserService;

    /**
     * 检查用户是否存在
     * @param body
     * @return
     */
    @PostMapping({ URLConstants.CHECKISREGIST_ANDROID,
            URLConstants.CHECKISREGIST_IOS,
            URLConstants.CHECKISREGIST_WECHAT,
            URLConstants.CHECKISREGIST_APPLET_WECHAT,
            URLConstants.CHECKISREGIST_APPLET_ALI })
    @ApiOperation(value = "检查用户是否存在", notes = "检查用户是否存在")
    public JsonResult checkUserIsExist(@Valid @RequestBody HttpReqBodyBasic<CheckWechatRegistReq> body, HttpServletRequest request, HttpServletResponse response){
        try {
            OuthSourceEnt outhSourceEnt = getOuthSourceEnt(request);
            return enterpriseUserService.checkUserIsExist(body.getParams(),outhSourceEnt);
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }

    }

    /**
     * 注册用户
     * @param body
     * @return
     */
    @PostMapping(value = "regUser")
    @ApiOperation(value = "注册用户", notes = "注册用户")
    public JsonResult regUser(@Valid @RequestBody HttpReqBodyBasic<EnterpriseUser> body, HttpServletRequest request, HttpServletResponse response){
        try {
            OuthSourceEnt outhSourceEnt = getOuthSourceEnt(request);
            return enterpriseUserService.add(body.getParams());
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }

    }

    @PostMapping({
            URLConstants.WECHATREGISTER_WECHAT,
            URLConstants.WECHATREGISTER_APPLET_WECHAT,
            URLConstants.WECHATREGISTER_APPLET_ALI })
    public void wechatRegister(@Valid @RequestBody HttpReqBodyBasic<WeChatRegisterReq> body,
                               HttpServletRequest request, HttpServletResponse response){
        try {
            OuthSourceEnt outhSourceEnt = getOuthSourceEnt(request);
            Object resp = userInfoRegistService.weChatRegister(body.getParams(),outhSourceEnt);
            sendJsonObject(response, resp);
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }
    }

}
