package com.luxury.mapper;

import com.luxury.model.TRole;
import java.util.List;

public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    int insertSelective(TRole record);

    TRole selectByPrimaryKey(Integer id);

    List<TRole> selectAll();

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);
}