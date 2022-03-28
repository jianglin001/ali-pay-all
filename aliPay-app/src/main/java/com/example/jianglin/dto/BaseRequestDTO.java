package com.example.jianglin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 15:54
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseRequestDTO implements Serializable {
    private String merchantNo;

    private String merchantRequestId;

    public BaseRequestDTO(String merchantNo, String merchantRequestId, String orderNo) {
        this.merchantNo = merchantNo;
        this.merchantRequestId = merchantRequestId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseRequestDTO{");
        sb.append("merchantNo='").append(merchantNo).append('\'');
        sb.append(", merchantRequestId='").append(merchantRequestId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
