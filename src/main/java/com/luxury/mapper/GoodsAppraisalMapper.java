package com.luxury.mapper;

import com.luxury.model.GoodsAppraisal;
import java.util.List;

public interface GoodsAppraisalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAppraisal record);

    int insertSelective(GoodsAppraisal record);

    GoodsAppraisal selectByPrimaryKey(Integer id);

    List<GoodsAppraisal> selectAll();

    int updateByPrimaryKeySelective(GoodsAppraisal record);

    int updateByPrimaryKey(GoodsAppraisal record);
}