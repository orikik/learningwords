package com.orikik.learningwords.exception;

public class LearningWordsException extends RuntimeException {
    private ErrorCodeEnum errorCode;
    private String errorMessage;

    public LearningWordsException(ErrorCodeEnum errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public LearningWordsException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getMessage();
    }

    public String getErrorCode() {
        return errorCode.name();
    }

    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
