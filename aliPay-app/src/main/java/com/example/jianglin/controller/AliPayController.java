package com.example.jianglin.controller;

import com.example.jianglin.dto.BaseResponseDTO;
import com.example.jianglin.entity.AliPayCallBackParams;
import com.example.jianglin.enums.PayExceptionEnums;
import com.example.jianglin.servlce.PayOrderScanService;
import com.example.jianglin.servlce.PayOrderService;
import com.example.jianglin.servlce.impl.AliPayServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 22:09
 */
@RequestMapping(value = "/aliPay")
@RestController
public class AliPayController {

    public final static Logger LOGGER = LoggerFactory.getLogger(AliPayController.class);

    @Autowired
    PayOrderScanService payOrderScanService;

    /**
     * 异步回调
     * @return
     */
    @RequestMapping(value = "/notifyCallBack", method = RequestMethod.POST)
    public Callable<BaseResponseDTO> activeScanCallback(AliPayCallBackParams aliPayCallBackParams) {
        return() -> {
            LOGGER.info("交易订单号：[{}]， 回调参数： [{}]", aliPayCallBackParams.getOut_trade_no(),  aliPayCallBackParams.toString());
            try {
                payOrderScanService.payOrderScanCallback(aliPayCallBackParams);
            } catch (Exception e) {
                LOGGER.info("支付宝回调异常：[{}],[{}]",e.getMessage(),e);
                return new BaseResponseDTO().setErrorMsg(PayExceptionEnums.PAY_ERROR.getCode());
            }
            return new BaseResponseDTO().setErrorMsg(PayExceptionEnums.PAY_SUCCESS.getCode());
        };
    }


}
