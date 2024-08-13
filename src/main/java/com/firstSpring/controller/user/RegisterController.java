package com.firstSpring.controller.user;

import com.firstSpring.controller.user.Validator.RegisterValidator;
import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;
import com.firstSpring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    UserService userService;

    @InitBinder
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
    public String savaUserProcess(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errorMsgs = result.getAllErrors();
            model.addAttribute("userDto",userDto);
            model.addAttribute("errorMsg", errorMsgs.get(0).getCode());
            return "register";
        }

        userService.saveCustJoinInfo(userDto);
        return "home";
    }

    // ID 중복확인 (POST)
    @PostMapping("/checkDuplicatedId")
    @ResponseBody
    @LogException
    public ResponseEntity<Map<String, String>> checkDuplicatedId(@RequestParam("id") String id) {
        Map<String, String> response = new HashMap<>();
        if (!userService.checkDuplicatedId(id)) {
            response.put("result", "fail");
        } else {
            response.put("result", "success");
            response.put("message", "사용가능한 아이디입니다.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // 이메일 중복확인 (POST)
    @PostMapping("/checkDuplicatedEmail")
    @ResponseBody
    @LogException
    public ResponseEntity<Map<String, String>>  checkDuplicatedEmail (String email) {
        Map<String, String> response = new HashMap<>();
        if (userService.findUserId(email)!=null) {
            response.put("result", "fail");
        } else {
            response.put("result", "success");
            response.put("message", "사용가능한 이메일주소입니다.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 이메일로 본인 인증 절차 진행 (POST)
    // > 입력받은 이메일 주소로 인증번호 전송
    @PostMapping("/checkEmail")
    @ResponseBody
    @LogException
    public ResponseEntity<Map<String,Object>> checkEmail(String email) {
        HashMap<String,Object> response = new HashMap();

        int checkNum = userService.sendMail(email);
        response.put("code",checkNum);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
