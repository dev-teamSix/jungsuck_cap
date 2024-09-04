package com.firstSpring.controller.user.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ResponseCode {

    DUPLICATED_ID(HttpStatus.CONFLICT,"! 이미 사용 중인 아이디입니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT,"! 이미 사용 중인 이메일입니다."),
    NOT_MATCH_ID_PWD(HttpStatus.BAD_REQUEST, "! 아이디와 비밀번호가 일치하지 않습니다."),
    NOT_MATCH_ID(HttpStatus.BAD_REQUEST,"! 일치하는 아이디가 없습니다. 다시 확인해주세요."),
    NOT_MATCH_ID_EMAIL(HttpStatus.BAD_REQUEST, "! 입력하신 회원 정보가 맞는지 다시 한번 확인해 주세요."),
    NOT_MATCH_PWD(HttpStatus.BAD_REQUEST,"비밀번호를 확인해주세요."),
    USER_REGISTRATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "회원가입을 실패하였습니다."),
    USER_DB_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"서버에서 문제가 발생했습니다. 잠시 후 다시 시도해주세요."),
    MAIL_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,"서버에서 문제가 발생했습니다. 잠시 후 다시 시도해주세요.");

    private final HttpStatus httpStatus;
    private final String message;
}
