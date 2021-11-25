package com.luxury.mapper;

import com.luxury.model.EnterpriseUser;
import java.util.List;
import java.util.Map;

public interface EnterpriseUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(EnterpriseUser record);

    int insertSelective(EnterpriseUser record);

    EnterpriseUser selectByPrimaryKey(String userId);

    List<EnterpriseUser> selectAll();

    int updateByPrimaryKeySelective(EnterpriseUser record);

    int updateByPrimaryKey(EnterpriseUser record);

    EnterpriseUser selectByParams(Map<String,Object> params);
}