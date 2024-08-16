package com.firstSpring.controller.user;

import com.firstSpring.controller.user.Validator.LoginValidator;
import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;
import com.firstSpring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    // validator 등록
    @InitBinder
    @LogException
    public void checkDataFormat(WebDataBinder binder) {
        binder.setValidator(new LoginValidator());
    }


    // 로그인 폼을 요청 (GET)
    @GetMapping("/form")
    @LogException
    public String moveToLoginForm() {
        return "login";
    }

    @PostMapping("/in")
    @LogException
    public String save(@Valid @ModelAttribute("userDto") UserDto userDto, Errors errors, boolean rememberId, HttpServletResponse response, HttpServletRequest request, RedirectAttributes ra) {
        // ID 및 PWD 유효성 검사
        //  유효성 검사 불일치 ->  login/form으로 리다이렉트
        if (errors.hasErrors()) {
            ra.addFlashAttribute("userDto",userDto); // 입력했던 기존 ID,PWD 전달
            Map<String,String> validatorResult = userService.validateHandling(errors);
            for(String key: validatorResult.keySet()) {
                ra.addFlashAttribute(key,validatorResult.get(key));
            }
            return "redirect:/login/form";
        }

        String id = userDto.getId();
        String pwd = userDto.getPwd();

        // ID 존재 유무 확인
        //  존재 X ->  login/form으로 리다이렉트
        if (!userService.checkExistOfId(id)) {
            ra.addFlashAttribute("userDto",userDto); // 입력했던 기존 ID,PWD 전달
            ra.addFlashAttribute("errorMsg", "존재하지 않는 아이디입니다.");
            return "redirect:/login/form";
        }

        // PWD 일치 여부 확인
        //  일치 X ->  login/form으로 리다이렉트
        if (!userService.checkPwdMatch(id, pwd)) {
            ra.addFlashAttribute("userDto",userDto); // 입력했던 기존 ID,PWD 전달
            ra.addFlashAttribute("errorMsg", "아이디,비밀번호가 일치하지 않습니다.");
            return "redirect:/login/form";
        }

        // ID 저장 체크박스 처리
        if (rememberId) {
            Cookie cookie = new Cookie("id", id);
            response.addCookie(cookie); // ID 저장
        } else {
            Cookie cookie = new Cookie("id", "");
            cookie.setMaxAge(0); // ID 삭제
            response.addCookie(cookie);
        }

        // 세션에 사용자 ID 저장
        HttpSession session = request.getSession();
        session.setAttribute("sessionUser", userService.getCustLoginInfo(id)); // 세션 객체에 userDto 저장

        // 메인 페이지로 리다이렉트
        return "redirect:/";
    }

    // 로그아웃
    // > 로그아웃 후 다시 login.jsp로 이동
    @GetMapping("/out")
    @LogException
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
