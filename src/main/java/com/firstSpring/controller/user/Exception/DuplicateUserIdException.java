package com.firstSpring.controller.user.Exception;

import lombok.Getter;

@Getter
public class DuplicateUserIdException extends UserCustomException {
    public DuplicateUserIdException(ResponseCode errorCode) {
        super(errorCode);
    }
}
