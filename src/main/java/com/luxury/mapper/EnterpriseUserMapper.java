package com.luxury.mapper;

import com.luxury.model.EnterpriseUser;

public interface EnterpriseUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(EnterpriseUser record);

    int insertSelective(EnterpriseUser record);

    EnterpriseUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(EnterpriseUser record);

    int updateByPrimaryKey(EnterpriseUser record);
}