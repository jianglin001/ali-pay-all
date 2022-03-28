package com.example.jianglin.servlce.impl;

import com.example.jianglin.build.PayOrderBuild;
import com.example.jianglin.dto.BaseResponseDTO;
import com.example.jianglin.entity.AliPayCallBackParams;
import com.example.jianglin.entity.PayOrder;
import com.example.jianglin.enums.AliPayStatusEnums;
import com.example.jianglin.enums.PayExceptionEnums;
import com.example.jianglin.servlce.PayOrderScanService;
import com.example.jianglin.servlce.PayOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-26 17:58
 */
@Service
public class PayOrderScanServiceImpl implements PayOrderScanService {
    public final static Logger LOGGER = LoggerFactory.getLogger(PayOrderScanServiceImpl.class);

    @Autowired
    PayOrderService payOrderService;

    @Override
    public BaseResponseDTO payOrderScanCallback(AliPayCallBackParams aliPayCallBackParams) {
        PayOrder payOrder = payOrderService.findByOrderNo(aliPayCallBackParams.getOut_trade_no());
        LOGGER.info("扫描当前订单:[{}]", payOrder.toString());
        if (AliPayStatusEnums.TRADE_SUCCESS.name().equals(aliPayCallBackParams.getTrade_status())) {
            LOGGER.info("支付宝支付成功,当前交易订单号: [{}]", aliPayCallBackParams.getOut_trade_no());
            payOrderService.updatePayOrder(PayOrderBuild.buildUpdateOrder(payOrder, aliPayCallBackParams));
        }
        return new BaseResponseDTO().setErrorCode(PayExceptionEnums.PAY_SUCCESS.getCode()).setErrorMsg(PayExceptionEnums.PAY_SUCCESS.getMessage());
    }
}
