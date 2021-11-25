package com.luxury.mapper;

import com.luxury.model.AppraisalConfigInfo;
import java.util.List;

public interface AppraisalConfigInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppraisalConfigInfo record);

    int insertSelective(AppraisalConfigInfo record);

    AppraisalConfigInfo selectByPrimaryKey(Integer id);

    List<AppraisalConfigInfo> selectAll();

    int updateByPrimaryKeySelective(AppraisalConfigInfo record);

    int updateByPrimaryKey(AppraisalConfigInfo record);
}