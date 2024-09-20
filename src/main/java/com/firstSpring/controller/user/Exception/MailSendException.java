package com.firstSpring.controller.user.Exception;

import lombok.Getter;

@Getter
public class MailSendException extends UserCustomException {
    public MailSendException(Throwable throwable,ResponseCode errorCode) {
        super(throwable,errorCode);
    }
}
