package com.firstSpring.controller.user;

import com.firstSpring.domain.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Slf4j
public class AuthenticateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션에 회원 정보가 존재하는지 확인
        HttpSession session = request.getSession();

        UserDto principal = (UserDto) session.getAttribute("sessionUser");
        if(principal == null) {
            String urlPrior = request.getRequestURI() + "?" + request.getQueryString();
            log.info("로그인 되지 않은 사용자의 요청 {}", urlPrior);
            request.getSession().setAttribute("url_prior_login",urlPrior); // 직전 url을 session에 담기

            response.sendRedirect("/login/form");
            return false;
        }
        return true;
    }
}
