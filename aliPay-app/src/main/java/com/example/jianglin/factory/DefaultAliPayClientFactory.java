package com.example.jianglin.factory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.example.jianglin.build.AliPayAndroidBuild;
import com.example.jianglin.config.AliPayParamsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 22:48
 */
@Component
public class DefaultAliPayClientFactory implements FactoryBean<AlipayClient>, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAliPayClientFactory.class);

    @Autowired
    AliPayParamsConfig aliPayParamsConfig;

    private AlipayClient alipayClient = null;


    @Override
    public AlipayClient getObject() throws Exception {
        return alipayClient;
    }

    @Override
    public Class<?> getObjectType() {
        return AlipayClient.class;
    }

    @Override
    public void afterPropertiesSet() {
        CertAlipayRequest request = AliPayAndroidBuild.buildCertAliPayRequest(aliPayParamsConfig);
        try {
            alipayClient = new DefaultAlipayClient(request);
        } catch (AlipayApiException e) {
            logger.error("支付宝初始化服务端捕获异常：[{}}", e.getMessage());
            return;
        }
        logger.info("创建支付宝网关访问客户端完成, 网关地址：{}，appId:{}", aliPayParamsConfig.getServiceUrl(), aliPayParamsConfig.getAppId());
    }
}
