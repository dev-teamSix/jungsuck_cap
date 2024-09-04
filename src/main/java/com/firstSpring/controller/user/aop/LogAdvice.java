package com.firstSpring.controller.user.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class LogAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    @AfterThrowing(value = "@annotation(com.firstSpring.controller.user.aop.LogException)",throwing = "exception")
    public void exception(JoinPoint joinPoint, Exception exception) {
        logger.error("error class : "+exception.getClass().getSimpleName());
        logger.error("error method name : "+joinPoint.getSignature().getName());
        logger.error("errorMessage : "+exception.getMessage());
    }

    @AfterReturning(value = "@annotation(com.firstSpring.controller.user.aop.LogException)",returning = "result")
    public void AfterReturning(JoinPoint joinPoint,Object result) {
        System.out.println("실행된 클래스 : "+ joinPoint.getTarget().getClass().getSimpleName());
        System.out.println("실행된 메서드 : "+ joinPoint.getSignature().getName());
        System.out.println("전달된 파라미터 : "+ Arrays.toString(joinPoint.getArgs()));
    }

}
