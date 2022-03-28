package com.example.jianglin.dto;

import com.example.jianglin.enums.PayOrderStatusEnums;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 15:54
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class BaseResponseDTO implements Serializable {
    private String errorCode;

    private String errorMsg;

    private PayOrderStatusEnums payStatus;

    private String payId;

    public BaseResponseDTO(String errorCode, String errorMsg, PayOrderStatusEnums payStatus, String payId) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.payStatus = payStatus;
        this.payId = payId;
    }

    @Override
    public String toString() {
        return "BaseResponseDTO{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", payStatus=" + payStatus +
                ", payId='" + payId + '\'' +
                '}';
    }
}
