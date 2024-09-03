package com.firstSpring.controller.user.Exception;

import lombok.Getter;
import org.springframework.dao.DataAccessException;

@Getter
public class UserDBException extends DataAccessException {
    private final ResponseCode errorCode;
    public UserDBException(Throwable cause, ResponseCode errorCode) {
        super(String.valueOf(cause));
        this.errorCode = errorCode;
    }
    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}
