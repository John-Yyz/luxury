package com.luxury.controller;

import com.luxury.config.URLConstants;
import com.luxury.request.JwtReq;
import com.luxury.service.IGoodsInfoService;
import com.luxury.service.IJwtService;
import com.luxury.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 描述：商品信息管理
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 1:43
 */
@Api(description = "验签管理", tags = "JwtController", basePath = "/jwt")
@RestController
@RequestMapping(URLConstants.JWT_CONTROLLER)
@Slf4j
public class JwtController {

    @Autowired(required = false)
    IJwtService jwtService;

    /**
     * 获取jwtToken
     * @param
     * @return
     */
    @PostMapping(value = "getJwtToken")
    @ApiOperation(value = "获取jwtToken", notes = "获取jwtToken")
    public JsonResult getJwtToken(@Valid @RequestBody JwtReq jwtReq){
        return jwtService.getJwtToken(jwtReq);
    }

}
