package com.firstSpring.controller.user.Exception;

import com.firstSpring.controller.user.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler({UserDBException.class})
    public ModelAndView handleAllException(UserDBException ex, Model model) {
        log.info("UserDBException: {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return new ModelAndView("error");
    }
}
