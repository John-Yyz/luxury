package com.luxury.mapper;

import com.luxury.model.TMeun;
import java.util.List;

public interface TMeunMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMeun record);

    int insertSelective(TMeun record);

    TMeun selectByPrimaryKey(Integer id);

    List<TMeun> selectAll();

    int updateByPrimaryKeySelective(TMeun record);

    int updateByPrimaryKey(TMeun record);
}