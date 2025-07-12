package com.example.backend_verdemais.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    public CustomException(ErrorCode errorCode, Object... args) {
        super(errorCode.getMessage(args));
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage(args);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
