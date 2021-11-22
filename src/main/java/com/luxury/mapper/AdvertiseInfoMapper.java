package com.luxury.mapper;

import com.luxury.model.AdvertiseInfo;

public interface AdvertiseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertiseInfo record);

    int insertSelective(AdvertiseInfo record);

    AdvertiseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdvertiseInfo record);

    int updateByPrimaryKey(AdvertiseInfo record);
}