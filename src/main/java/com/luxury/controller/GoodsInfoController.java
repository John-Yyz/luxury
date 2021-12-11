package com.luxury.controller;

import com.luxury.service.IGoodsInfoService;
import com.luxury.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：商品信息管理
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 1:43
 */
@Api(description = "【APP】商品信息管理", tags = "GoodsInfoController", basePath = "/goodsInfo")
@RestController
@RequestMapping("/v1/goodsInfo")
@Slf4j
public class GoodsInfoController {

    @Autowired(required = false)
    IGoodsInfoService goodsInfoService;

    /**
     * 获取商品列表
     * @param
     * @return
     */
    @PostMapping(value = "getGoodsInfoList")
    @ApiOperation(value = "获取商品列表", notes = "获取商品列表")
    public JsonResult getGoodsInfoList(){
        return goodsInfoService.getGoodsInfoList();
    }

}
