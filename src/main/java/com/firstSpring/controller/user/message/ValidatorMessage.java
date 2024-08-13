package com.firstSpring.controller.user.message;

public enum ValidatorMessage {
    // 아이디
    ID_MISSING("아이디를 입력하지 않으셨습니다."),
    DUPLICATED_ID("중복된 아이디입니다."),
    ID_LENGTH_OUT_OF_BOUNDS("아이디의 길이는 5이상 10이하로 입력해야 합니다."),
    INVALID_ID_FORMAT("아이디 입력란에는 한글, 특수문자, 공백 입력이 불가합니다."),

    // 비밀번호
    PASSWORD_MISSING("비밀번호를 입력하지 않으셨습니다."),
    WRONG_PASSWORD("비밀번호가 일치하지 않습니다."),
    MISMATCHED_PASSWORD("동일한 비밀번호를 입력하지 않으셨습니다."),
    PASSWORD_LENGTH_OUT_OF_BOUNDS("비밀번호의 길이는 8이상 30이하로 입력해야 합니다."),
    INVALID_PASSWORD_FORMAT("비밀번호는 영문자, 숫자, 특수문자를 사용한 조합으로 입력해야 합니다."),
    SEQUENTIAL_CHARACTERS("비밀번호에 4개이상 연속된 숫자는 입력할 수 없습니다."),
    PASSWORD_CONTAINS_ID("비밀번호에 아이디를 포함할 수 없습니다."),

    // 이메일
    EMAIL_MISSING("이메일을 입력하지 않으셨습니다."),
    DUPLICATED_EMAIL("이미 사용 중인 이메일입니다."),
    INVALID_EMAIL_FORMAT("잘못된 이메일 형식으로 입력하셨습니다."),

    // 메일 인증번호
    EMAIL_KEY_MISSING("인증번호를 입력하지 않으셨습니다."),
    EMAIL_KEY_LENGTH_OUT_OF_BOUNDS("인증번호는 최대 6자리까지 입력할 수 있습니다."),
    INVALID_EMAIL_KEY_FORMAT("인증번호는 숫자만 입력할 수 있습니다."),

    // 이름
    NAME_MISSING("이름을 입력하지 않으셨습니다."),
    NAME_LENGTH_OUT_OF_BOUNDS("이름의 길이는 2이상으로 입력해야 합니다."),
    INVALID_NAME_FORMAT("이름은 한글만 입력 가능합니다."),

    // 성별
    GENDER_MISSING("성별을 체크하여야 합니다."),

    // 생년월일
    BIRTH_INCLUDE_WHITESPACE("생년월일에 공백이 입력되었습니다."),
    BIRTH_LENGTH_OUT_OF_BOUNDS("생년월일은 8자리로 입력하십시오."),
    INVALID_BIRTH_FORMAT("생년월일을 잘못 입력하셨습니다."),

    // 휴대폰번호
    INVALID_MOBILE_NUMBER_FORMAT("휴대폰번호는 숫자만 입력 가능합니다."),
    MOBILE_NUMBER_LENGTH_OUT_OF_BOUNDS("휴대폰은 최대 13글자까지 입력이 가능합니다."),
    INVALID_MOBILE_NUMBER_FORMAT_2("휴대폰은 하이폰을 포함하여 입력해야 하며, 숫자와 하이폰만 입력 해주시길 바랍니다.");


    private final String message;

    ValidatorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
