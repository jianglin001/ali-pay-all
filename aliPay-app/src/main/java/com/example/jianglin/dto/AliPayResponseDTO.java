package com.example.jianglin.dto;

import com.example.jianglin.enums.PayOrderStatusEnums;
import lombok.*;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 15:53
 */
@Getter
@Setter
@NoArgsConstructor
public class AliPayResponseDTO extends BaseResponseDTO {

    private String aliPayBody;

    @Builder
    public AliPayResponseDTO(String errorCode, String errorMsg, PayOrderStatusEnums payStatus, String payId, String aliPayBody) {
        super(errorCode, errorMsg, payStatus, payId);
        this.aliPayBody = aliPayBody;
    }
}
