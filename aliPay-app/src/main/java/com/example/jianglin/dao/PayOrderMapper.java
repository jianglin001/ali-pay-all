package com.example.jianglin.dao;

import com.example.jianglin.entity.PayOrder;

import java.util.List;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-26 15:15
 */
public interface PayOrderMapper {

    PayOrder findByMerchantRequestId(String merchantRequestId);

    PayOrder findByOrderNo(String orderNo);

    List<PayOrder> findByDoing();

    void insertPayOrder(PayOrder payOrder);

    void updatePayOrder(PayOrder payOrder);
}
