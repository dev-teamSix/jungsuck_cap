package com.firstSpring.controller.user.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserCustomException extends RuntimeException{
    private final ResponseCode errorCode;

    public UserCustomException(ResponseCode errorCode) {
        this.errorCode = errorCode;
    }
    public UserCustomException(Throwable throwable, ResponseCode errorCode) {
        super(throwable);
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}
