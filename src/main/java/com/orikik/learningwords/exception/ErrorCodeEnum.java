package com.orikik.learningwords.exception;

public enum ErrorCodeEnum {
    OPERATION_PROHIBITED_EXCEPTION("Operation in prohibited"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    ;

    private String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
