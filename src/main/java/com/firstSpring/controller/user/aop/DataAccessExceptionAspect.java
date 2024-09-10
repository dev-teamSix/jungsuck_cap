package com.firstSpring.controller.user.aop;

import com.firstSpring.controller.user.Exception.UserDBException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import static com.firstSpring.controller.user.Exception.UserErrorCode.USER_DB_ERROR;

@Aspect
@Component
public class DataAccessExceptionAspect {

    @AfterThrowing(pointcut = "execution(* com.firstSpring.service.user.*.*(..))", throwing = "ex")
    public void handleDataAccessException(DataAccessException ex) throws UserDBException {
        // 예외를 UserDBException으로 변환하여 던짐
        throw new UserDBException(ex, USER_DB_ERROR);
    }
}
