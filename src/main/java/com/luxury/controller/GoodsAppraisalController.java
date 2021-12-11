package com.luxury.controller;

import com.luxury.model.GoodsAppraisal;
import com.luxury.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：鉴定记录管理
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 1:43
 */
@Api(description = "【APP】鉴定记录管理", tags = "GoodsAppraisalController", basePath = "/goodsAppraisal")
@RestController
@RequestMapping("/v1/goodsAppraisal")
@Slf4j
public class GoodsAppraisalController {

    /**
     * 添加在线鉴定记录
     * @param
     * @return
     */
    @PostMapping(value = "addGoodsAppraisal")
    @ApiOperation(value = "添加在线鉴定记录", notes = "添加在线鉴定记录")
    public JsonResult addGoodsAppraisal(@RequestBody GoodsAppraisal goodsAppraisal){
        return JsonResult.success("成功");
    }

    /**
     * 编辑在线鉴定记录
     * @param
     * @return
     */
    @PostMapping(value = "editGoodsAppraisal")
    @ApiOperation(value = "编辑在线鉴定记录", notes = "编辑在线鉴定记录")
    public JsonResult editGoodsAppraisal(@RequestBody GoodsAppraisal goodsAppraisal){
        return JsonResult.success("成功");
    }

    /**
     * 修改在线鉴定记录
     * @param
     * @return
     */
    @PostMapping(value = "updateGoodsAppraisal")
    @ApiOperation(value = "修改在线鉴定记录", notes = "修改在线鉴定记录")
    public JsonResult updateGoodsAppraisal(@RequestBody GoodsAppraisal goodsAppraisal){
        return JsonResult.success("成功");
    }
}
