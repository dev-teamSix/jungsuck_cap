package com.firstSpring.controller.user.Exception;

import com.firstSpring.controller.user.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalRestControllerExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler({DataAccessException.class})
//    public ResponseEntity<ApiResponse<Object>> handleAllException(DataAccessException ex) {
//        log.info("UserException: {}", ex.getMessage());
//        return handleExceptionInternal(ex.getErrorCode());
//    }

    @ExceptionHandler({UserCustomException.class})
    public ResponseEntity<ApiResponse<Object>> handleAllException(UserCustomException ex) {
        log.info("UserException: {}", ex.getMessage());
        return handleExceptionInternal(ex.getErrorCode());
    }

    private ResponseEntity<ApiResponse<Object>> handleExceptionInternal(ResponseCode responseCode) {
        ApiResponse<Object> fail = ApiResponse.fail(responseCode, null);
        return ResponseEntity
                .status(fail.getHeader().getCode())
                .body(fail);
    }
}
