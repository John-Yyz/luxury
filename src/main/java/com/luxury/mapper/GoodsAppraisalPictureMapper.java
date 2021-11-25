package com.luxury.mapper;

import com.luxury.model.GoodsAppraisalPicture;
import java.util.List;

public interface GoodsAppraisalPictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAppraisalPicture record);

    int insertSelective(GoodsAppraisalPicture record);

    GoodsAppraisalPicture selectByPrimaryKey(Integer id);

    List<GoodsAppraisalPicture> selectAll();

    int updateByPrimaryKeySelective(GoodsAppraisalPicture record);

    int updateByPrimaryKey(GoodsAppraisalPicture record);
}