package com.example.jianglin.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 15:52
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AliPayRequestDTO extends BaseRequestDTO{
    private String goodsId;

    private String goodsName;

    private String goodsType;

    private BigDecimal payAmount;

    private String productCode;

    private String notifyUrl;

    private String returnUrl;


}
