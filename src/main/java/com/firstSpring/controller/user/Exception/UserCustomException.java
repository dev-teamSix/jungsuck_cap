package com.firstSpring.controller.user.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCustomException extends RuntimeException{
    private final ResponseCode errorCode;
    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}
