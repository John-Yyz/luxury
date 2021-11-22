package com.luxury.mapper;

import com.luxury.model.GoodsAssess;

public interface GoodsAssessMapper {
    int deleteByPrimaryKey(String assessId);

    int insert(GoodsAssess record);

    int insertSelective(GoodsAssess record);

    GoodsAssess selectByPrimaryKey(String assessId);

    int updateByPrimaryKeySelective(GoodsAssess record);

    int updateByPrimaryKey(GoodsAssess record);
}