package com.luxury.mapper;

import com.luxury.model.PayOrderInfo;

public interface PayOrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayOrderInfo record);

    int insertSelective(PayOrderInfo record);

    PayOrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayOrderInfo record);

    int updateByPrimaryKey(PayOrderInfo record);
}