package com.firstSpring.controller.user.Validator;

import com.firstSpring.domain.user.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.firstSpring.controller.user.message.ValidatorMessage.*;

public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        validateId(userDto.getId(), errors);
        validatePwd(userDto.getPwd(), errors);
    }

    public void validateId(String id, Errors errors) {
        // ID : 입력하지 않았거나 공백 들어간 경우
        if (id == null || id.trim().isEmpty()) {
            errors.rejectValue("id", ID_MISSING.getMessage());
            return;
        }

        // ID : 지정된 글자수 범위를 벗어난 경우 (3자 이상, 20자 이하)
        if (id.length() < 3 || id.length() > 20) {
            errors.rejectValue("id", ID_LENGTH_OUT_OF_BOUNDS.getMessage());
        }

        // ID : 한글, 특수문자 사용 허용 안함 (알파벳, 숫자만 허용)
        if (!id.matches("^[a-zA-Z0-9]*")) {
            errors.rejectValue("id", INVALID_ID_FORMAT.getMessage());
        }

    }

    public void validatePwd(String pwd, Errors errors) {
        if (pwd == null || pwd.trim().isEmpty()) {
            errors.rejectValue("pwd", PASSWORD_MISSING.getMessage());
            return;
        }

        // 비밀번호 8이상 30이하
        if (pwd.length() < 8 || pwd.length() > 30) {
            errors.rejectValue("pwd", PASSWORD_LENGTH_OUT_OF_BOUNDS.getMessage());
        }

        // 연속적인 4자리 숫자 검사
        if (hasSequentialNumbers(pwd)) {
            errors.rejectValue("pwd", SEQUENTIAL_CHARACTERS.getMessage());
        }
    }

    private boolean hasSequentialNumbers(String pwd) {
        for (int i = 0; i < pwd.length() - 3; i++) {
            char a = pwd.charAt(i);
            char b = pwd.charAt(i + 1);
            char c = pwd.charAt(i + 2);
            char d = pwd.charAt(i + 3);

            if (Character.isDigit(a) && Character.isDigit(b) && Character.isDigit(c) && Character.isDigit(d)) {
                if (a + 1 == b && b + 1 == c && c + 1 == d) {
                    return true;
                }
            }
        }
        return false;
    }
}
