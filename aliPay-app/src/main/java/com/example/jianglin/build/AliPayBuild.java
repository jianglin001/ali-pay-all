package com.example.jianglin.build;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.example.jianglin.dto.AliPayResponseDTO;
import com.example.jianglin.enums.PayExceptionEnums;
import com.example.jianglin.enums.PayOrderStatusEnums;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 16:32
 */
public class AliPayBuild {

    /**
     * 响应阿里返回的错误数据
     * @param exception
     * @param aliPayId
     * @return
     */
    public static AliPayResponseDTO buildAliPayException(AlipayApiException exception, String aliPayId){
        return AliPayResponseDTO.builder()
                .errorCode(exception.getErrCode())
                .errorMsg(exception.getErrMsg())
                .payStatus(PayOrderStatusEnums.FAIL)
                .payId(aliPayId)
                .build();
    }

    /**
     * 响应内部错误码数据
     * @param aliPayId
     * @param code
     * @param msg
     * @return
     */
    public static AliPayResponseDTO buildAliPayFail(String aliPayId, String code, String msg) {
        return AliPayResponseDTO.builder()
                .errorCode(code)
                .errorMsg(msg)
                .aliPayBody(null)
                .payId(aliPayId)
                .build();
    }

    /**
     * 响应成功参数
     * @param aliPayId
     * @param response
     * @return
     */
    public static AliPayResponseDTO buildAliPaySuccess(String aliPayId, AlipayTradeAppPayResponse response) {
        return AliPayResponseDTO.builder()
                .payId(aliPayId)
                .aliPayBody(response.getBody())
                .errorCode(PayExceptionEnums.PAY_CREATE_SUCCESS.getCode())
                .errorMsg(PayExceptionEnums.PAY_CREATE_SUCCESS.getMessage())
                .payStatus(PayOrderStatusEnums.SUCCESS)
                .build();
    }

}
