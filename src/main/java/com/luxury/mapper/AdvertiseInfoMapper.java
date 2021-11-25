package com.luxury.mapper;

import com.luxury.model.AdvertiseInfo;
import java.util.List;

public interface AdvertiseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdvertiseInfo record);

    int insertSelective(AdvertiseInfo record);

    AdvertiseInfo selectByPrimaryKey(Integer id);

    List<AdvertiseInfo> selectAll();

    int updateByPrimaryKeySelective(AdvertiseInfo record);

    int updateByPrimaryKey(AdvertiseInfo record);
}