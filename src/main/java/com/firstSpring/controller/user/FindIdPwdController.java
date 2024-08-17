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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/find")
public class FindIdPwdController {

    private UserService userService;
    private ResponseHandler responseHandler;

    private final static Map<String,Object> response = new HashMap<>();
    @Autowired
    public FindIdPwdController(UserService userService,ResponseHandler responseHandler){
        this.userService = userService;
        this.responseHandler = responseHandler;
    }

    // 예외 처리 메서드
    @ExceptionHandler(CustException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustException ex) {
        response.put("result", "fail");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    // 부여된 인증번호 업데이트
    private void extracted(Integer checkNum, UserDto userDto) throws Exception {
        // Integer 타입의 checkNum을 문자열로 변환
        String mailKeyString = checkNum.toString();
        userDto.setMailKey(mailKeyString);
        userService.saveCustMailKey(userDto);
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

    // 본인인증
    // 아이디&이메일로 조회시
    //  > null이 아니면 인증번호 발송
    //  > null이면 fail(실패)
    @PostMapping("/authentication")
    @LogException
    public ResponseEntity<Map<String, Object>>  getUserUpdateAuthentication(String id,String email) throws Exception{
        UserDto userDto = userService.findUserIdAndEmail(id,email);
        // 받아온 아이디와 이메일로 dto null체크
        if(userDto == null) {
            throw new CustException("가입 시 입력하신 회원 정보가 맞는지 다시 한번 확인해 주세요.", HttpStatus.BAD_REQUEST);
        }else {
            // 인증번호 발송
            Integer checkNum = userService.sendMail(email);

            if (checkNum == null) {
                // 인증번호 발송 실패 시
                response.put("result", "error"); // login.jsp 인증번호 (ajax) 수정 필요할듯
                throw new CustException("서버와 통신 중 에러가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // 부여된 인증번호 업데이트
            extracted(checkNum, userDto);

            // 성공 응답
            responseHandler.add("code", checkNum);
            return responseHandler.successHandler("인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요.");
        }
    }


    // 비밀번호 찾기 -> 비밀번호 수정
    // 메일에 임시번호 포함해서 보내주기
    @PostMapping("/pwd")
    @LogException
    public ResponseEntity<Map<String, Object>> getUserUpdatePw(String id,String pwd) {
        // modifyUserPwd 메서드에서 가입회원인지 아닌지 확인함
        //      > 가입회원인지 확인 후 비밀번호 변경 시도 (true반환)
        //      > 가입회원이 아닌 경우 비밀번호 변경 실패 (false반환)
        if(!userService.modifyUserPwd(id,pwd)) {
            throw new CustException("해당하는 아이디가 존재하지 않습니다", HttpStatus.BAD_REQUEST);
        }else {
            return responseHandler.successHandler("비밀번호 변경에 성공하였습니다.");
        }
    }

}
