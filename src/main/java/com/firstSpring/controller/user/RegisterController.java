package com.firstSpring.controller.user;

import com.firstSpring.controller.user.Validator.RegisterValidator;
import com.firstSpring.controller.user.response.ApiResponse;
import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;
import com.firstSpring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.firstSpring.controller.user.Exception.UserSuccessCode.*;

@Controller
@RequestMapping("/user")
public class RegisterController {

    private UserService userService;
    @Autowired
    public RegisterController(UserService userService){
        this.userService = userService;
    }

    // validator 등록
    @InitBinder
    @LogException
    public void checkDataFormat(WebDataBinder binder) {
        binder.setValidator(new RegisterValidator());
    }

    // 회원가입 (GET)
    @GetMapping("/register")
    @LogException
    public String registerPage() {
        return "register";
    }

    // 회원가입 (POST)
    @PostMapping("/save")
    @LogException
    public String savaUserProcess(@Valid @ModelAttribute("userDto") UserDto userDto, Errors errors, Model model) {
        userService.saveCustJoinInfo(userDto);
        return "redirect:/login/form";
    }

    // ID 중복확인 (POST)
    @PostMapping("/checkDuplicatedId")
    @ResponseBody
    @LogException
    public ApiResponse<UserDto> checkDuplicatedId(@RequestParam("id") String id) {
        return ApiResponse.success(userService.checkDuplicatedId(id),ID_AVAILABLE.getMessage());
    }


    // 이메일 중복확인 (POST)
    @PostMapping("/checkDuplicatedEmail")
    @ResponseBody
    @LogException
    public ApiResponse<UserDto> checkDuplicatedEmail(String email) {
        return ApiResponse.success(userService.checkDuplicatedEmail(email),EMAIL_AVAILABLE.getMessage());
    }


    // 이메일로 본인 인증 절차 진행 (POST)
    // > 입력받은 이메일 주소로 인증번호 전송
    @PostMapping("/checkEmail")
    @ResponseBody
    @LogException
    public ApiResponse<Integer> checkEmail(String email) {
        return ApiResponse.success(userService.sendMail(email),AUTH_NUM_SEND_SUCCESS.getMessage());
    }
}