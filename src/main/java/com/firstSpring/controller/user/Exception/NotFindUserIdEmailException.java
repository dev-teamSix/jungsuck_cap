package com.firstSpring.controller.user.Exception;

import lombok.Getter;
@Getter
public class NotFindUserIdEmailException extends UserCustomException{
    public NotFindUserIdEmailException(ResponseCode errorCode) {
        super(errorCode);
    }
}
