package com.firstSpring.controller.user;

import com.firstSpring.controller.user.Exception.CustException;
import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;
import com.firstSpring.entity.ResponseHandler;
import com.firstSpring.service.user.UserService;
import com.firstSpring.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/find")
public class FindIdPwdController {

    private UserService userService;
    private ResponseHandler responseHandler;

    private final BCryptPasswordEncoder passwordEncoder;

    private final static Map<String,Object> response = new HashMap<>();
    @Autowired
    public FindIdPwdController(UserService userService, ResponseHandler responseHandler, BCryptPasswordEncoder passwordEncoder){
        this.userService = userService;
        this.responseHandler = responseHandler;
        this.passwordEncoder = passwordEncoder;
    }

    // 예외 처리 메서드
    @ExceptionHandler(CustException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustException ex) {
        response.put("result", "fail");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    //아이디 찾기
    @PostMapping("/id")
    @LogException
    public ResponseEntity<Map<String, Object>> getUserIdByNameAndEmail(String name, String email) {
        // 받아온 이름과 이메일로 ID가 담긴 dto 가져오기
        UserDto userDto = userService.findUserId(name,email);

        // dto==null -> fail
        // dto!=null -> dto전달
        if(userDto == null) {
            throw new CustException("일치하는 아이디가 없습니다. 다시 확인해주세요.", HttpStatus.BAD_REQUEST);
        }else {
            responseHandler.add("userDto",userDto);
            return responseHandler.successHandler(String.format("찾으시는 ID는 %s 입니다.", userDto.getId()));
        }
    }

    // 임시 비밀번호 이메일 전송
    // 아이디&이메일로 조회시
    //  > null이 아니면 이메일 발송
    //  > null이면 fail(실패)
    @PostMapping("/pwd")
    @LogException
    public ResponseEntity<Map<String, Object>>  getUserUpdatePwd(String id,String email) throws Exception{
        UserDto userDto = userService.findUserIdAndEmail(id,email);
        // 받아온 아이디와 이메일로 dto null체크
        if(userDto == null) {
            throw new CustException("가입 시 입력하신 회원 정보가 맞는지 다시 한번 확인해 주세요.", HttpStatus.BAD_REQUEST);
        }else {
            // 임시비밀번호 이메일 발송
            String tempPassword = userService.sendTempPassword(id,email);

            if (tempPassword == null) {
                // 임시비밀번호 발송 실패 시
                response.put("result", "error"); // login.jsp 인증번호 (ajax) 수정 필요할듯
                throw new CustException("서버와 통신 중 에러가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // 성공 응답
            responseHandler.add("code", tempPassword);
            return responseHandler.successHandler("임시 비밀번호 발송이 완료되었습니다. 입력한 이메일에서 임시 비밀번호 확인을 해주세요.");
        }
    }


    // 임시 비밀번호와 매개변수로 들어온 비밀번호가 일치하는지 확인
    @PostMapping("/updatePw")
    @LogException
    public ResponseEntity<Map<String, Object>> getUserUpdatePw(String id,String pwd) {
        if(!authenticatePwd(pwd,userService.getCustLoginInfo(id).getPwd())) {
            throw new CustException("비밀번호를 확인해주세요.", HttpStatus.BAD_REQUEST);
        }else {
            return responseHandler.successHandler("로그인 후에 비밀번호를 변경해주세요!");
        }
    }

    // 암호화 전후 비밀번호 비교
    // 암호화 전 비밀번호(rawPwd)와 암호화 후 비밀번호(encodedPwd)
    private boolean authenticatePwd(String rawPwd, String encodedPwd) {
        return passwordEncoder.matches(rawPwd, encodedPwd);
    }

}
