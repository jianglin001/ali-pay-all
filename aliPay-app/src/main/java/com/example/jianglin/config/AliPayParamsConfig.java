package com.example.jianglin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 16:08
 */
@ConfigurationProperties(value = "alipay")
@Configuration
@Getter
@Setter
public class AliPayParamsConfig {
    private String appId;

    private String serviceUrl;

    private String rootKey;

    private String appPrivateKey;

    private String appPayPublicKey;

    @Override
    public String toString() {
        return "AliPayParamsConfig{" +
                "appId='" + appId + '\'' +
                ", serviceUrl='" + serviceUrl + '\'' +
                ", rootKey='" + rootKey + '\'' +
                ", appPrivateKey='" + appPrivateKey + '\'' +
                ", aliPayPublicKey='" + appPayPublicKey + '\'' +
                '}';
    }
}

