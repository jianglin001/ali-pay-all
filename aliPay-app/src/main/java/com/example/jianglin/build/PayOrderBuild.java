package com.example.jianglin.build;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.example.jianglin.dto.AliPayRequestDTO;
import com.example.jianglin.entity.AliPayCallBackParams;
import com.example.jianglin.entity.PayOrder;
import com.example.jianglin.enums.AliPayStatusEnums;
import com.example.jianglin.enums.PayOrderStatusEnums;
import com.example.jianglin.enums.PayTypeEnums;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-26 17:00
 */
public class PayOrderBuild {

    public static PayOrder buildSavePayOrder(PayOrder payOrder, AliPayRequestDTO requestDTO) {
        payOrder.setMerchantRequestId(requestDTO.getMerchantRequestId());
        payOrder.setPayAmount(requestDTO.getPayAmount());
        payOrder.setOrderAmount(requestDTO.getPayAmount());
        payOrder.setPayType(PayTypeEnums.ZFB.name());
        payOrder.setNofityUrl(requestDTO.getNotifyUrl());
        payOrder.setReturnUrl(requestDTO.getReturnUrl());
        payOrder.setPayTime(new Date());
        payOrder.setOrderStatus(PayOrderStatusEnums.DOING.name());
        payOrder.setGoodsName(requestDTO.getGoodsName());
        payOrder.setGoodsType(requestDTO.getGoodsType());
        payOrder.setCreateTime(new Date());
        payOrder.setUpdateTime(new Date());
        return payOrder;
    }

    public static PayOrder buildUpdateOrder(PayOrder payOrder, AliPayCallBackParams aliPayCallBackParams) {
        payOrder.setPayAmount(new BigDecimal(aliPayCallBackParams.getReceipt_amount()));
        payOrder.setOrderNo(aliPayCallBackParams.getOut_trade_no());
        payOrder.setChannelTradeNo(aliPayCallBackParams.getTrade_no());
        payOrder.setOrderStatus(PayOrderStatusEnums.SUCCESS.name());
        payOrder.setUpdateTime(new Date());
        return payOrder;
    }

    public static PayOrder buildUpdateTaskOrder(String orderId, AlipayTradeQueryResponse tradeQueryResponse) {
        PayOrder payOrder = new PayOrder();
        payOrder.setPayAmount(new BigDecimal(tradeQueryResponse.getReceiptAmount()));
        payOrder.setOrderNo(orderId);
        payOrder.setChannelTradeNo(tradeQueryResponse.getTradeNo());
        payOrder.setOrderStatus(PayOrderStatusEnums.SUCCESS.name());
        payOrder.setUpdateTime(new Date());
        return payOrder;
    }
}
