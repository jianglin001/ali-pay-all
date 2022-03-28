package com.example.jianglin.servlce;

import com.alipay.api.AlipayClient;
import com.example.jianglin.dto.BaseRequestDTO;
import com.example.jianglin.entity.PayOrder;
import com.example.jianglin.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 22:53
 */
public class BaseService {
    public final static Logger LOGGER = LoggerFactory.getLogger(BaseService.class);
    @Autowired
    protected AlipayClient alipayClient;

    @Autowired
    public PayOrderService payOrderService;

    public void setAliPayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }
    /**
     * 查询订单是否唯一
     * @return
     */
    public PayOrder publicOrderCheck(BaseRequestDTO requestDTO) {
        String aliPayId = UUIDUtils.getInstance().generateShortUuid();
        PayOrder payOrder = payOrderService.findByMerchantRequestId(requestDTO.getMerchantRequestId());
        if (Objects.isNull(payOrder)) {
            payOrder = new PayOrder();
        }
        payOrder.setOrderNo(aliPayId);
        LOGGER.info("当前订单号为:[{}]", aliPayId);
        return payOrder;
    }
}
