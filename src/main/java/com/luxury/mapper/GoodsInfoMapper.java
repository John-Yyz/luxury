package com.luxury.mapper;

import com.luxury.model.GoodsInfo;
import java.util.List;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(String goodsId);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    List<GoodsInfo> selectAll();

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
}