package com.luxury.mapper;

import com.luxury.model.PayOrderInfo;
import java.util.List;

public interface PayOrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayOrderInfo record);

    int insertSelective(PayOrderInfo record);

    PayOrderInfo selectByPrimaryKey(Integer id);

    List<PayOrderInfo> selectAll();

    int updateByPrimaryKeySelective(PayOrderInfo record);

    int updateByPrimaryKey(PayOrderInfo record);
}