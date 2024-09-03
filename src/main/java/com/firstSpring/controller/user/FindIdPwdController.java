package com.firstSpring.controller.user;

import com.firstSpring.controller.user.response.ApiResponse;
import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;
import com.firstSpring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.firstSpring.controller.user.Exception.UserSuccessCode.*;

@RestController
@RequestMapping("/find")
public class FindIdPwdController {

    private final UserService userService;
    @Autowired
    public FindIdPwdController(UserService userService){
        this.userService = userService;
    }

    //아이디 찾기
    @PostMapping("/id")
    @LogException
    public ApiResponse<UserDto> getUserIdByNameAndEmail(String name, String email) {
        return ApiResponse.success(userService.findUserId(name,email), USER_FOUND_SUCCESS.getMessage(userService.findUserId(name,email).getId()));
    }

    // 임시 비밀번호 이메일 전송
    // 아이디&이메일로 조회시
    //  > null이 아니면 이메일 발송
    //  > null이면 fail(실패)
    @PostMapping("/pwd")
    @LogException
    public ApiResponse<String> getUserUpdatePwd(String id, String email) {
        userService.findUserIdAndEmail(id,email);

        // 임시비밀번호 이메일 발송
        String tempPassword = userService.sendTempPassword(id,email);

        // 메일 서비스 추상화 할때 수정할 부분
//        if (tempPassword == null) {
//            // 임시비밀번호 발송 실패 시
//            response.put("result", "error"); // login.jsp 인증번호 (ajax) 수정 필요할듯
//            throw new CustException("서버와 통신 중 에러가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        // 성공 응답
        return ApiResponse.success(tempPassword, TEMP_PWD_SEND_SUCCESS.getMessage());
    }


    // 임시 비밀번호와 매개변수로 들어온 비밀번호가 일치하는지 확인
    @PostMapping("/updatePw")
    @LogException
    public ApiResponse getUserUpdatePw(String id, String pwd) {
        userService.matchPwd(pwd,userService.getCustLoginInfo(id).getPwd());
        return ApiResponse.success(CHANGE_PWD_SUCCESS.getMessage());
    }
}
