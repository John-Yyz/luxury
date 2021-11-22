package com.luxury.mapper;

import com.luxury.model.GoodsAppraisalPicture;

public interface GoodsAppraisalPictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAppraisalPicture record);

    int insertSelective(GoodsAppraisalPicture record);

    GoodsAppraisalPicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsAppraisalPicture record);

    int updateByPrimaryKey(GoodsAppraisalPicture record);
}