package com.firstSpring.controller.user.aop;

import com.firstSpring.domain.user.UserDto;
import com.firstSpring.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Slf4j
@Component
@Aspect
public class ValidationCheckAdvice {

    @Autowired
    UserService userService;

    @Around("execution(* com.firstSpring.controller.user.*.*(..))")
    public Object validationCheck(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        Errors errors = null;
        UserDto userDto = null;
        Model model = null;

        // 인자 목록에서 BindingResult, Model, userDto를 찾기
        for (Object arg : args) {
            if (arg instanceof Errors) {
                errors = (Errors) arg;
            } else if (arg instanceof Model) {
                model = (Model) arg;
            } else if (arg instanceof UserDto) {
                userDto = (UserDto) arg;
            }
        }

        // BindingResult에 오류가 있는 경우 처리
        if (errors != null && errors.hasErrors()) {
            log.info("BindingResult ERROR: {}", errors);
            if (model != null && userDto != null) {
                log.info("BindingResult DTO: {}", userDto);

                // 회원가입 실패시 입력 데이터 값을 유지
                model.addAttribute("userDto", userDto);
                // 유효성 통과 못한 필드와 메시지를 핸들링
                Map<String,String> validatorResult = userService.validateHandling(errors);
                for(String key: validatorResult.keySet()) {
                    model.addAttribute(key,validatorResult.get(key));
                }
                return "register";
            }
        }
        // 에러가 없는 경우 메서드 실행을 계속 진행
        return jp.proceed();
    }
}

