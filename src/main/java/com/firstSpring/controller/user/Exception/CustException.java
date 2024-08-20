package com.firstSpring.controller.user.Exception;

import org.springframework.http.HttpStatus;

public class CustException  extends RuntimeException {
    private HttpStatus status;

    public CustException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
