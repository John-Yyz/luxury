package com.luxury.mapper;

import com.luxury.model.GoodsAssess;
import java.util.List;

public interface GoodsAssessMapper {
    int deleteByPrimaryKey(String assessId);

    int insert(GoodsAssess record);

    int insertSelective(GoodsAssess record);

    GoodsAssess selectByPrimaryKey(String assessId);

    List<GoodsAssess> selectAll();

    int updateByPrimaryKeySelective(GoodsAssess record);

    int updateByPrimaryKey(GoodsAssess record);
}