package com.example.jianglin.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 17:30
 */
@Getter
@Setter
public class PayException extends RuntimeException {
    private static final long serialVersionUID = 8109469326798389194L;

    private String systemCode;
    private String errorCode;
    private String errorMsg;

    public PayException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }
}
