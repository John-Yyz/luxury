package com.luxury.controller;

import com.luxury.config.QiNiuToken;
import com.luxury.config.URLConstants;
import com.luxury.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/18 17:23
 */
@Api(description = "公共工具SDK接入等", tags = "UtilsController", basePath = "/utilsController")
@RestController("utilsController")
@RequestMapping(URLConstants.UTILS_CONTROLLER)
public class UtilsController {

    @Resource
    QiNiuToken qiNiuToken;

    @ApiOperation(value = "获取七牛云图片存储的token", notes = "获取七牛云图片存储的token")
    @RequestMapping(value = "getQiNiuToken", method = RequestMethod.GET)
    public JsonResult getQiNiuToken(){
        return JsonResult.success(qiNiuToken.getQiniuToken());
    }
}
