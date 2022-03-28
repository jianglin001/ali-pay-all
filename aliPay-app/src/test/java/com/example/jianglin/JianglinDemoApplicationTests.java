package com.example.jianglin;

import com.example.jianglin.dto.AliPayRequestDTO;
import com.example.jianglin.servlce.AliPayService;
import com.example.jianglin.util.UUIDUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class JianglinDemoApplicationTests {

    @Autowired
    AliPayService aliPayService;


    @Test
    public void test() {
        AliPayRequestDTO requestDTO = new AliPayRequestDTO();
        requestDTO.setGoodsId(System.currentTimeMillis() + " ");
        requestDTO.setGoodsName("测试商品");
        requestDTO.setGoodsType("0");
        requestDTO.setNotifyUrl("http://aezg5h.natappfree.cc/aliPay/notifyCallBack");
        requestDTO.setReturnUrl("www.baidu.com");
        requestDTO.setPayAmount(new BigDecimal(12));
        requestDTO.setProductCode("QUICK_MSECURITY_PAY");
        requestDTO.setMerchantRequestId(UUIDUtils.getInstance().generateShortUuid());
        requestDTO.setMerchantNo("3792739823424235");
        System.out.println(aliPayService.aliPayAndroidApp(requestDTO));
    }
}
