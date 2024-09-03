package com.firstSpring.controller.user.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements ResponseCode {

    USER_FOUND_SUCCESS(HttpStatus.OK, "찾으시는 ID는 %s 입니다."),
    TEMP_PWD_SEND_SUCCESS(HttpStatus.OK,"임시 비밀번호 발송이 완료되었습니다. 입력한 이메일에서 임시 비밀번호 확인을 해주세요."),
    AUTH_NUM_SEND_SUCCESS(HttpStatus.OK,"인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요."),
    CHANGE_PWD_SUCCESS(HttpStatus.OK,"로그인 후에 비밀번호를 변경해주세요!"),
    ID_AVAILABLE(HttpStatus.OK, "✔ 사용 가능한 아이디입니다."),
    EMAIL_AVAILABLE(HttpStatus.OK, "✔ 사용 가능한 이메일주소입니다.");



    // 메시지 포맷팅을 위한 메서드 추가
    public String formatMessage(Object... args) {
        return String.format(this.message, args);
    }

    public String getMessage(Object... args) {
        return formatMessage(args);
    }

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
