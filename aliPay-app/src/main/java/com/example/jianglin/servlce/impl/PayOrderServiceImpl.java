package com.example.jianglin.servlce.impl;

import com.example.jianglin.dao.PayOrderMapper;
import com.example.jianglin.entity.PayOrder;
import com.example.jianglin.enums.PayExceptionEnums;
import com.example.jianglin.exception.PayException;
import com.example.jianglin.servlce.PayOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-26 15:57
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {

    public final static Logger LOGGER = LoggerFactory.getLogger(PayOrderServiceImpl.class);

    @Autowired
    PayOrderMapper payOrderMapper;

    @Override
    public PayOrder findByMerchantRequestId(String merchantRequestId) {
        PayOrder payOrder = payOrderMapper.findByMerchantRequestId(merchantRequestId);
        if (!Objects.isNull(payOrder)) {
            LOGGER.warn("当前订单已存在 请求号为: [{}]", merchantRequestId);
            throw new PayException(PayExceptionEnums.PAY_UNIQUE.getCode(), PayExceptionEnums.PAY_UNIQUE.getMessage());
        }
        return payOrder;
    }

    @Override
    public PayOrder findByOrderNo(String orderNo) {
        return payOrderMapper.findByOrderNo(orderNo);
    }

    @Override
    @Transactional
    public void insertPayOrder(PayOrder payOrder) {
        payOrderMapper.insertPayOrder(payOrder);
    }

    @Override
    public void updatePayOrder(PayOrder payOrder) {
        payOrderMapper.updatePayOrder(payOrder);
    }
}
