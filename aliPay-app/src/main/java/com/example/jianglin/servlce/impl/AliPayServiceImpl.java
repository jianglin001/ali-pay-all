package com.example.jianglin.servlce.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.example.jianglin.build.AliPayAndroidBuild;
import com.example.jianglin.build.AliPayBuild;
import com.example.jianglin.build.PayOrderBuild;
import com.example.jianglin.config.AliPayParamsConfig;
import com.example.jianglin.dto.AliPayRequestDTO;
import com.example.jianglin.dto.AliPayResponseDTO;
import com.example.jianglin.entity.PayOrder;
import com.example.jianglin.enums.PayExceptionEnums;
import com.example.jianglin.exception.PayException;
import com.example.jianglin.servlce.AliPayService;
import com.example.jianglin.servlce.BaseService;
import com.example.jianglin.servlce.PayOrderService;
import com.example.jianglin.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 15:51
 */
@Service
public class AliPayServiceImpl extends BaseService implements AliPayService {

    public final static Logger LOGGER = LoggerFactory.getLogger(AliPayServiceImpl.class);

    AliPayParamsConfig aliPayParamsConfig;
    PayOrderService payOrderService;

    @Autowired
    public AliPayServiceImpl(AliPayParamsConfig aliPayParamsConfig, PayOrderService payOrderService) {
        this.aliPayParamsConfig = aliPayParamsConfig;
        this.payOrderService = payOrderService;
    }


    @Override
    public AliPayResponseDTO aliPayAndroidApp(AliPayRequestDTO aliPayRequestDTO) {
        PayOrder payOrder = null;
        try {
           payOrder =  super.publicOrderCheck(aliPayRequestDTO);
        } catch (PayException e) {
            LOGGER.error("订单初始化异常:[{}]", e.getMessage());
            return AliPayBuild.buildAliPayFail(payOrder.getOrderNo() , e.getErrorCode(), e.getErrorMsg());
        }
        // 创建初始化订单
        payOrderService.insertPayOrder(PayOrderBuild.buildSavePayOrder(payOrder, aliPayRequestDTO));

        // 创建支付宝交易订单
        AlipayTradeAppPayRequest alipayTradeAppPayRequest = AliPayAndroidBuild.buildAliPayTradeRequest(payOrder.getOrderNo(), aliPayRequestDTO);

        LOGGER.info("支付宝交易订单请求参数 : [{}], 当前订单id为: [{}]", JSON.toJSONString(alipayTradeAppPayRequest), payOrder.getOrderNo());
        AlipayTradeAppPayResponse alipayTradeAppPayResponse = null;
        try {
             alipayTradeAppPayResponse = alipayClient.sdkExecute(alipayTradeAppPayRequest);
            LOGGER.info("调用支付宝交易订单响应参数: [{}]", JSON.toJSONString(alipayTradeAppPayResponse));
        } catch (AlipayApiException e) {
            LOGGER.error("创建支付宝交易订单捕获异常：[{}}", e.getMessage());
            return AliPayBuild.buildAliPayException(e, payOrder.getOrderNo());
        }
        if (!alipayTradeAppPayResponse.isSuccess()) {
            new PayException(PayExceptionEnums.PAY_ERROR.getCode(), PayExceptionEnums.PAY_ERROR.getMessage());
        }

        return AliPayBuild.buildAliPaySuccess(payOrder.getOrderNo(), alipayTradeAppPayResponse);
    }
}
