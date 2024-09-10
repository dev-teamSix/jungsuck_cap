package com.firstSpring.controller.user.Exception;

import lombok.Getter;

@Getter
public class NotFindPwdException extends UserCustomException {
    public NotFindPwdException(ResponseCode errorCode) {
        super(errorCode);
    }
}
