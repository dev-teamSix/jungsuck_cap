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
import org.springframework.validation.Errors;
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
    private UserService userService;

    // response에 정보를 담을 map
    private final static Map<String, Object> response = new HashMap<>();

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
        if (errors.hasErrors()) {
            // 회원가입 실패시 입력 데이터 값을 유지
            model.addAttribute("userDto",userDto);
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String,String> validatorResult = userService.validateHandling(errors);
            for(String key: validatorResult.keySet()) {
                model.addAttribute(key,validatorResult.get(key));
            }
            return "register";
        }

        // saveCustJoinInfo 결과가
        // false -> fail
        // true -> success
        if(!userService.saveCustJoinInfo(userDto)){
            // 회원가입 실패시 입력 데이터 값을 유지
            model.addAttribute("userDto",userDto);
            model.addAttribute("errorMsg", "회원가입에 실패하셨습니다.");
            return "register";
        }

        // 회원가입 성공시 로그인폼으로 이동
        return "/login/form";
    }

    // ID 중복확인 (POST)
    @PostMapping("/checkDuplicatedId")
    @ResponseBody
    @LogException
    public ResponseEntity<Map<String, Object>> checkDuplicatedId(@RequestParam("id") String id) {
        // 아이디 중복이면 -> fail
        // 아이디 중복이 아니면 -> success
        if (!userService.checkDuplicatedId(id)) {
            response.put("result", "fail");
            response.put("message", "! 이미 사용중인 아이디입니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("result", "success");
            response.put("message", "✔ 사용 가능한 아이디입니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    // 이메일 중복확인 (POST)
    @PostMapping("/checkDuplicatedEmail")
    @ResponseBody
    @LogException
    public ResponseEntity<Map<String, Object>> checkDuplicatedEmail(String email) {
        // 이메일 중복이면 -> fail
        // 이메일 중복이 아니면 -> success
        if (!userService.checkDuplicatedEmail(email)) {
            response.put("result", "fail");
            response.put("message", "!  이메일이 이미 사용중입니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("result", "success");
            response.put("message", "✔ 사용 가능한 이메일주소입니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    // 이메일로 본인 인증 절차 진행 (POST)
    // > 입력받은 이메일 주소로 인증번호 전송
    @PostMapping("/checkEmail")
    @ResponseBody
    @LogException
    public ResponseEntity<Map<String,Object>> checkEmail(String email) {
        Integer checkNum = userService.sendMail(email); // 메일 인증번호 전송

        // 인증번호가 null -> fail
        // null이 아니면 -> 인증번호 전달
        if (checkNum == null) {
            response.put("result", "fail");
            response.put("message","서버와 통신 중 에러가 발생했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response.put("result", "success");
            response.put("message","인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요.");
            response.put("code", checkNum);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
