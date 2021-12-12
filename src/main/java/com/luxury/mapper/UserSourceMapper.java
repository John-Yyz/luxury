package com.luxury.mapper;

import com.luxury.model.UserSource;
import java.util.List;
import java.util.Map;

public interface UserSourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSource record);

    int insertSelective(UserSource record);

    UserSource selectByPrimaryKey(Integer id);

    List<UserSource> selectAll();

    int updateByPrimaryKey(UserSource record);

    UserSource selectByUserMobileAndSource(Map<String,Object> params);

    UserSource findUserIsRegistByOpenId(Map<String,Object> params);

    UserSource findUserIsRegistByUnionid(Map<String,Object> params);
}