package com.luxury.service.impl;

import com.luxury.base.BaseQuery;
import com.luxury.mapper.GoodsInfoMapper;
import com.luxury.model.GoodsInfo;
import com.luxury.service.IGoodsInfoService;
import com.luxury.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/28 21:47
 */
@Service(value = "goodsInfoService")
public class GoodsInfoServiceImpl implements IGoodsInfoService {

    @Autowired(required = false)
    GoodsInfoMapper goodsInfoMapper;

    /**
     * 根据查询条件获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        return JsonResult.success("成功",goodsInfoMapper.selectAll());
    }

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Map<String, Object> info(Integer id) {
        return null;
    }

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        return null;
    }

    /**
     * 根据实体对象添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult add(GoodsInfo entity) {
        return null;
    }

    /**
     * 根据实体对象更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult update(GoodsInfo entity) {
        return null;
    }

    /**
     * 根据实体对象添加、编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(GoodsInfo entity) {
        return null;
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(GoodsInfo entity) {
        return null;
    }

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public JsonResult deleteById(Integer id) {
        return null;
    }

    /**
     * 根据ID删除记录
     *
     * @param ids 记录ID
     * @return
     */
    @Override
    public JsonResult deleteByIds(String ids) {
        return null;
    }

    @Override
    public JsonResult getGoodsInfoList() {
        Map<String,Object> params = new HashMap<>(1);
        //启用状态
        params.put("status",1);
        return JsonResult.success("成功",goodsInfoMapper.selectAllByParams(params));
    }
}
