package com.luxury.mapper;

import com.luxury.model.EnterpriseInfo;
import java.util.List;
import java.util.Map;

public interface EnterpriseInfoMapper {
    int deleteByPrimaryKey(String enterpriseId);

    int insert(EnterpriseInfo record);

    int insertSelective(EnterpriseInfo record);

    EnterpriseInfo selectByPrimaryKey(String enterpriseId);

    List<EnterpriseInfo> selectAll();

    int updateByPrimaryKeySelective(EnterpriseInfo record);

    int updateByPrimaryKey(EnterpriseInfo record);

    EnterpriseInfo selectBySelect(Map<String,Object> params);
}