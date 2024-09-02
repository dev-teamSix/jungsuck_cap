package com.firstSpring.controller.user.Exception;

public class DuplicateUserEmailException extends RuntimeException {
    public DuplicateUserEmailException(Throwable cause) {
        super(cause);
    }
}
