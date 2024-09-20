package com.firstSpring.controller.user.Exception;

import lombok.Getter;

@Getter
public class NotFindUserIdException extends UserCustomException {
    public NotFindUserIdException(ResponseCode errorCode) {
        super(errorCode);
    }
}
