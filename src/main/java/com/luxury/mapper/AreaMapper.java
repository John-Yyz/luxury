package com.luxury.mapper;

import com.luxury.model.Area;
import java.util.List;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    List<Area> selectAll();

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}