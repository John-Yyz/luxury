package com.luxury.mapper;

import com.luxury.model.SystemUser;

public interface SystemUserMapper {
    int deleteByPrimaryKey(String manageUserId);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(String manageUserId);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);
}