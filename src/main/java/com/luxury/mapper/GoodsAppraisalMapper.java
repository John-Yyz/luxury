package com.luxury.mapper;

import com.luxury.model.GoodsAppraisal;

public interface GoodsAppraisalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAppraisal record);

    int insertSelective(GoodsAppraisal record);

    GoodsAppraisal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsAppraisal record);

    int updateByPrimaryKey(GoodsAppraisal record);
}