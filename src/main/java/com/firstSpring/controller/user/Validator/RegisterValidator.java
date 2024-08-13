package com.firstSpring.controller.user.Validator;

import com.firstSpring.domain.user.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.DateTimeException;
import java.time.LocalDate;

import static com.firstSpring.controller.user.message.ValidatorMessage.*;

public class RegisterValidator implements Validator {

    // 검증하려는 객체가 UserDto 타입인지 확인
    // clazz 가 UserDto 또는 그 자손인지 확인
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    // target: 검사 대상 객체, errors: 에러코드 설정 위한 객체
    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        validateId(userDto.getId(), errors);
        validateName(userDto.getName(), errors);
        validatePwd(userDto.getPwd(),userDto.getId(), errors);
        validateEmail(userDto.getEmail(), errors);
        validateBirth(userDto.getBirth(), errors);
        validateMobileNum(userDto.getPhNum(), errors);
        validateGender(userDto.getGender(), errors);
        validateMailKey(userDto.getMailKey(), errors);
    }

    // 메일 인증번호 유효성 검사
    private void validateMailKey(String mailKey, Errors errors) {
        // 메일 인증번호 : 입력하지 않았거나 공백이 들어간 경우
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mailKey", EMAIL_KEY_MISSING.getMessage());

        // 메일 인증번호 : 지정된 글자수 범위를 벗어난 경우 (최대 6자리)
        if (mailKey.length() > 6) {
            errors.rejectValue("mailKey", EMAIL_KEY_LENGTH_OUT_OF_BOUNDS.getMessage());
        }

        // 메일 인증번호 : 숫자 형식이 아닌 경우
        if (!mailKey.matches("^[0-9]*$")) {
            errors.rejectValue("mailKey", INVALID_EMAIL_KEY_FORMAT.getMessage());
        }
    }

    // 성별 유효성 검사
    private void validateGender(String gender, Errors errors) {
        // 성별 : 입력하지 않았거나 공백이 들어간 경우
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", GENDER_MISSING.getMessage());
    }


    // 아이디 유효성 검증
    public void validateId(String id, Errors errors) {
        // ID : 입력하지 않았거나 공백 들어간 경우
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", ID_MISSING.getMessage());

        // ID : 지정된 글자수 범위를 벗어난 경우 (5자 이상, 10자 이하)
        if (id.length() < 5 || id.length() > 10) {
            errors.rejectValue("id", ID_LENGTH_OUT_OF_BOUNDS.getMessage());
        }

        // ID : 한글, 특수문자 사용 허용 안함 (알파벳, 숫자만 허용)
        if (!id.matches("^[a-zA-Z0-9]*")) {
            errors.rejectValue("id", INVALID_ID_FORMAT.getMessage());
        }
    }


    // 이름 유효성 검증
    public void validateName(String name, Errors errors) {
        // Name : 입력하지 않았거나 공백 들어간 경우
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", NAME_MISSING.getMessage());

        // Name : 2자 이상 입력하지 않은 경우
        if (name.length() < 2) {
            errors.rejectValue("name", NAME_LENGTH_OUT_OF_BOUNDS.getMessage());
        }

        // Name : 한글만 입력 가능
        if (!name.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
            errors.rejectValue("name", INVALID_NAME_FORMAT.getMessage());
        }
    }

    // 비밀번호 유효성 검증
    public void validatePwd(String pwd,String id, Errors errors) {
        String pwd1 = pwd.split(",")[0];
        String pwd2 = pwd.split(",")[1];

        // 비밀번호(pwd1) 및 비밀번호 확인(pwd2) 값 동일하게 입력했는지 확인
        if (!pwd1.equals(pwd2)) {
            errors.rejectValue("pwd", MISMATCHED_PASSWORD.getMessage());
        }

        // PWD : 입력하지 않았거나 공백 들어간 경우
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", PASSWORD_MISSING.getMessage());

        // PWD : 지정된 글자수 범위를 벗어난 경우 (8자 이상, 30자 이하)
        if (pwd1.length() < 8 || pwd1.length() > 30) {
            errors.rejectValue("pwd", PASSWORD_LENGTH_OUT_OF_BOUNDS.getMessage());
        }

        // PWD : 4자리 이상의 연속적인 숫자 제한
        for (int i = 0; i < pwd1.length() - 3; i++) {
            char a = pwd1.charAt(i);
            char b = pwd1.charAt(i + 1);
            char c = pwd1.charAt(i + 2);
            char d = pwd1.charAt(i + 3);

            if (a + 1 == b && b + 1 == c && c + 1 == d) {
                errors.rejectValue("pwd", SEQUENTIAL_CHARACTERS.getMessage());
                break;
            }
        }

        // PWD : 영문, 숫자, 특수문자 조합 (cf. 특수문자 종류 제한 없음)
        if (!pwd1.matches("^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[~!@#$%^ &*()_+.,?]).{8,30}$")) {
            errors.rejectValue("pwd", INVALID_PASSWORD_FORMAT.getMessage());
        }

        // PWD : ID 포함시
        if (pwd1.contains(id)) {
            errors.rejectValue("pwd", PASSWORD_CONTAINS_ID.getMessage());
        }
    }

    // 이메일 유효성 검증
    public void validateEmail(String email, Errors errors) {
        // Email : 입력하지 않았거나 공백 들어간 경우
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", EMAIL_MISSING.getMessage());

        // Email: format 제한
        if (!email.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")) {
            errors.rejectValue("email", INVALID_EMAIL_FORMAT.getMessage());
        }
    }

    // 생년월일 - 유효성 검증 메서드
    public void validateBirth(String birth, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birth", BIRTH_INCLUDE_WHITESPACE.getMessage());

        if (birth.length() != 10) {
            errors.rejectValue("birth", BIRTH_LENGTH_OUT_OF_BOUNDS.getMessage());
        }

        // 생년월일을 파싱하여 연도, 월, 일로 분리 2000-07-14
        int intYear = Integer.parseInt(birth.substring(0, 4));
        int intMonth = Integer.parseInt(birth.substring(5, 7));
        int intDay = Integer.parseInt(birth.substring(8, 10));

        // 현재 날짜
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        // 기본적인 범위 확인
        if (!(intYear >= 1900 && intYear <= currentYear && intMonth >= 1 && intMonth <= 12 && intDay >= 1 && intDay <= 31)) {
            errors.rejectValue("birth", "INVALID_BIRTH_FORMAT");
            return;
        }

        try {
            // 생년월일을 LocalDate 객체로 생성
            LocalDate birthDate = LocalDate.of(intYear, intMonth, intDay);

            // 생년월일이 현재 날짜를 초과하는지 확인
            if (birthDate.isAfter(today)) {
                errors.rejectValue("birth", INVALID_BIRTH_FORMAT.getMessage());
            }
        } catch (DateTimeException e) {
            e.printStackTrace();
            errors.rejectValue("birth", INVALID_BIRTH_FORMAT.getMessage());
        }
    }


    // 핸드폰번호 - 유효성 검증 메서드
    public void validateMobileNum(String phNum, Errors errors) {
        // 하이픈을 포함하여 입력해야하며, 숫자와 하이픈만 허용
        if (!phNum.matches("^01[0-1|6-9]-[0-9]{3,4}-[0-9]{4}$")) {
            errors.rejectValue("phNum", INVALID_MOBILE_NUMBER_FORMAT_2.getMessage());
        }

        // 13글자까지만 허용
        if (phNum.length() > 13) {
            errors.rejectValue("phNum", MOBILE_NUMBER_LENGTH_OUT_OF_BOUNDS.getMessage());
        }
    }


}
