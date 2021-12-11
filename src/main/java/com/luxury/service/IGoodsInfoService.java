package com.luxury.service;

import com.luxury.base.IBaseService;
import com.luxury.model.GoodsInfo;
import com.luxury.utils.JsonResult;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/28 21:40
 */
@Service
public interface IGoodsInfoService extends IBaseService<GoodsInfo> {

    JsonResult getGoodsInfoList();
}
