package com.firstSpring.controller.user;

import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;
import com.firstSpring.service.user.UserService;
import com.firstSpring.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/find")
public class FindIdPwdController {

    @Autowired
    private UserService userService;

    // response에 정보를 담을 map
    private final static Map<String, Object> response = new HashMap<>();


    //아이디 찾기
    @PostMapping("/id")
    @LogException
    public ResponseEntity<Map<String, Object>> getUserIdByNameAndEmail(String name, String email) {
        // 받아온 이름과 이메일로 ID가 담긴 dto 가져오기
        UserDto userDto = userService.findUserId(name,email);

        // dto==null -> fail
        // dto!=null -> dto전달
        if(userDto == null) {
            response.put("result","fail");
            response.put("message","일치하는 아이디가 없습니다. 다시 확인해주세요.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            response.put("result","success");
            response.put("message", String.format("찾으시는 ID는 %s 입니다.", userDto.getId()));
            response.put("userDto",userDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
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
            response.put("result","fail");
            response.put("message","가입 시 입력하신 회원 정보가 맞는지 다시 한번 확인해 주세요.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            // 인증번호 발송
            Integer checkNum = userService.sendMail(email);

            if (checkNum == null) {
                // 인증번호 발송 실패 시
                response.put("result", "error");
                response.put("message", "서버와 통신 중 에러가 발생했습니다.");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // 부여된 인증번호 업데이트
            // Integer 타입의 checkNum을 문자열로 변환
            String mailKeyString = checkNum.toString();
            userDto.setMailKey(mailKeyString);
            userService.saveCustMailKey(userDto);

            // 성공 응답
            response.put("result", "success");
            response.put("code", checkNum);
            response.put("message", "인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요.");
            return new ResponseEntity<>(response, HttpStatus.OK);
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
            response.put("result","fail");
            response.put("message","해당하는 아이디가 존재하지 않습니다");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            response.put("result","success");
            response.put("message", "비밀번호 변경에 성공하였습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
