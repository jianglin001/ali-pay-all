package com.example.jianglin.enums;

import lombok.Getter;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 17:33
 */
@Getter
public enum PayExceptionEnums {
    PAY_CREATE_SUCCESS("PAY00000", "支付订单创建成功"),
    PAY_SUCCESS("PAY00001", "支付成功"),
    PAY_UNIQUE("PAY50001", "订单已存在"),
    PAY_ERROR("PAY99999", "支付异常");

    private String code;
    private String message;

    PayExceptionEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
