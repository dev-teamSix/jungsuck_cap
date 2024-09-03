package com.firstSpring.controller.user.Exception;

import lombok.Getter;

@Getter
public class DuplicateUserEmailException extends UserCustomException {
    public DuplicateUserEmailException(ResponseCode errorCode) {
        super(errorCode);
    }
}
