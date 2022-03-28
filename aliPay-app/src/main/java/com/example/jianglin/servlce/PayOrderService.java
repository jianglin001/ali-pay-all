package com.example.jianglin.servlce;

import com.example.jianglin.entity.PayOrder;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-26 15:56
 */
public interface PayOrderService {

    PayOrder findByMerchantRequestId(String merchantRequestId);

    PayOrder findByOrderNo(String orderNo);

    void insertPayOrder(PayOrder payOrder);

    void updatePayOrder(PayOrder payOrder);
}
