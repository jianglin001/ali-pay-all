package com.example.jianglin.task;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.example.jianglin.build.AliPayAndroidBuild;
import com.example.jianglin.build.PayOrderBuild;
import com.example.jianglin.dao.PayOrderMapper;
import com.example.jianglin.entity.PayOrder;
import com.example.jianglin.enums.AliPayStatusEnums;
import com.example.jianglin.servlce.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-26 18:24
 */
@Component
public class PayOrderQueryTask extends BaseService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PayOrderQueryTask.class);

    @Autowired
    PayOrderMapper payOrderMapper;

    @Scheduled(cron = "*/30 * * * * ?")
    public void execute() {
        List<PayOrder> payOrderDoingLists = payOrderMapper.findByDoing();
        LOGGER.info("定时轮询扫描开始共有：[{}] 条", payOrderDoingLists.size());
        for (PayOrder o : payOrderDoingLists) {
            LOGGER.info("当前交易订单号为:[{}]", o.getOrderNo());
            AlipayTradeQueryRequest request = AliPayAndroidBuild.buildTradeQueryRequest(o.getOrderNo());
            LOGGER.info("请求支付宝交易订单查询参数:[{}]", JSON.toJSONString(request));
            AlipayTradeQueryResponse tradeQueryResponse = null;
            try {
                tradeQueryResponse = alipayClient.certificateExecute(request);
                LOGGER.info("订单状态：[{}], 响应支付宝订单查询参数：[{}]", tradeQueryResponse.getTradeStatus(), JSON.toJSONString(tradeQueryResponse));
            } catch (AlipayApiException e) {
                LOGGER.error("系统错误：[{}}", e.getErrMsg());
                return;
            }
            if (tradeQueryResponse.isSuccess()) {
                if (AliPayStatusEnums.WAIT_BUYER_PAY.name().equals(tradeQueryResponse.getTradeStatus())) {
                    LOGGER.warn("创建交易，继续等待买家付款，订单状态:[{}}, 订单号: [{}]", tradeQueryResponse.getTradeStatus(), o.getOrderNo());
                    continue;
                }
                payOrderService.updatePayOrder(PayOrderBuild.buildUpdateTaskOrder(o.getOrderNo(), tradeQueryResponse));
            }
        }
    }
}
