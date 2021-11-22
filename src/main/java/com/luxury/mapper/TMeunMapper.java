package com.luxury.mapper;

import com.luxury.model.TMeun;

public interface TMeunMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMeun record);

    int insertSelective(TMeun record);

    TMeun selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMeun record);

    int updateByPrimaryKey(TMeun record);
}