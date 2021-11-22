package com.luxury.mapper;

import com.luxury.model.GoodsInfo;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(String 商品id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(String 商品id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
}