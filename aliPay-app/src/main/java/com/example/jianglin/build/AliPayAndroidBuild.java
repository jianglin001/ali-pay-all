package com.example.jianglin.build;

import com.alipay.api.CertAlipayRequest;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.example.jianglin.config.AliPayParamsConfig;
import com.example.jianglin.constants.AliPayConstants;
import com.example.jianglin.dto.AliPayRequestDTO;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 16:03
 */
public class AliPayAndroidBuild {

    public static CertAlipayRequest buildCertAliPayRequest(AliPayParamsConfig aliPayParamsConfig) {
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(aliPayParamsConfig.getServiceUrl());
        certAlipayRequest.setAppId(aliPayParamsConfig.getAppId());
        certAlipayRequest.setPrivateKey(aliPayParamsConfig.getAppPrivateKey());
        certAlipayRequest.setFormat("JSON");
        certAlipayRequest.setCharset(AliPayConstants.CHARSET);
        certAlipayRequest.setSignType(AliPayConstants.SIGN_TYPE);
        certAlipayRequest.setCertPath(AliPayConstants.CERT_KEY_PATH);
        certAlipayRequest.setAlipayPublicCertPath(AliPayConstants.ROOT_PUBLIC_KEY_PATH);
        certAlipayRequest.setRootCertPath(AliPayConstants.ROOT_KEY_PATH );
        return certAlipayRequest;
    }

    public static AlipayTradeAppPayRequest buildAliPayTradeRequest(String aliPayId, AliPayRequestDTO aliPayRequestDTO) {
        AlipayTradeAppPayModel alipayTradeAppPayModel = new AlipayTradeAppPayModel();
        alipayTradeAppPayModel.setTotalAmount(String.valueOf(aliPayRequestDTO.getPayAmount()));
        alipayTradeAppPayModel.setProductCode(aliPayRequestDTO.getProductCode());
        alipayTradeAppPayModel.setOutTradeNo(aliPayId);
        alipayTradeAppPayModel.setSubject(aliPayRequestDTO.getGoodsId());
        alipayTradeAppPayModel.setBody(aliPayRequestDTO.getGoodsName());
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setNotifyUrl(aliPayRequestDTO.getNotifyUrl());
        request.setReturnUrl(aliPayRequestDTO.getReturnUrl());
        request.setApiVersion("2.0");
        request.setBizModel(alipayTradeAppPayModel);
        request.setProdCode(aliPayRequestDTO.getProductCode());
        return request;
    }


    public static AlipayTradeQueryRequest buildTradeQueryRequest(String orderNo) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel queryModel = new AlipayTradeQueryModel();
        queryModel.setOutTradeNo(orderNo);
        request.setBizModel(queryModel);
        return request;
    }
}
