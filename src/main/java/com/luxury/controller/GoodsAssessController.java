package com.luxury.controller;

import com.luxury.model.GoodsAppraisal;
import com.luxury.model.GoodsAssess;
import com.luxury.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：估价记录管理
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 1:43
 */
@Api(description = "【APP】估价记录管理", tags = "GoodsAssessController", basePath = "/goodsAssess")
@RestController
@RequestMapping("/v1/goodsAssess")
@Slf4j
public class GoodsAssessController {

    /**
     * 添加在线估价记录
     * @param
     * @return
     */
    @PostMapping(value = "addGoodsAssess")
    @ApiOperation(value = "添加在线估价记录", notes = "添加在线估价记录")
    public JsonResult addGoodsAppraisal(@RequestBody GoodsAssess goodsAssess){
        return JsonResult.success("成功");
    }

    /**
     * 编辑在线估价记录
     * @param
     * @return
     */
    @PostMapping(value = "editGoodsAssess")
    @ApiOperation(value = "编辑在线估价记录", notes = "编辑在线估价记录")
    public JsonResult editGoodsAssess(@RequestBody GoodsAssess goodsAssess){
        return JsonResult.success("成功");
    }

    /**
     * 修改在线估价记录
     * @param
     * @return
     */
    @PostMapping(value = "updateGoodsAssess")
    @ApiOperation(value = "修改在线估价记录", notes = "修改在线估价记录")
    public JsonResult updateGoodsAssess(@RequestBody GoodsAssess goodsAssess){
        return JsonResult.success("成功");
    }
}
