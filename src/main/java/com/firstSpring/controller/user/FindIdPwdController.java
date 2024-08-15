package com.firstSpring.controller.user;

import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;
import com.firstSpring.service.user.UserService;
import com.firstSpring.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String,Object> getUserIdByNameAndEmail(String name,String email) {
        // 받아온 이름과 이메일로 ID가 담긴 dto 가져오기
        UserDto userDto = userService.findUserId(name,email);

        // dto==null -> fail
        // dto!=null -> dto전달
        if(userDto == null) {
            response.put("result","fail");
        }else {
            response.put("result","success");
            response.put("userDto",userDto);
        }

        return response;
    }

    // 본인인증
    // 아이디&이메일로 조회시
    //  > null이 아니면 인증번호 발송
    //  > null이면 fail(실패)
    @PostMapping("/authentication")
    @LogException
    public Map<String,Object> getUserUpdateAuthentication(String id,String email) throws Exception{
        UserDto userDto = userService.findUserIdAndEmail(id,email);
        // 받아온 아이디와 이메일로 dto null체크
        if(userDto == null) {
            response.put("result","fail");
        }else {
            // 인증번호 발송
            int checkNum = userService.sendMail(email);
            // 부여된 인증번호 업데이트
            userDto.setMailKey(String.valueOf((checkNum)));
            userService.saveCustMailKey(userDto);

            // 해당 인증번호 전달
            response.put("result","success");
            response.put("code",checkNum);
        }

        return response;
    }

    // 비밀번호 찾기 -> 비밀번호 수정
    @PostMapping("/pwd")
    @LogException
    public Map<String,Object> getUserUpdatePw(String id,String pwd) {
        // modifyUserPwd 메서드에서 가입회원인지 아닌지 확인함
        //      > 가입회원인지 확인 후 비밀번호 변경 시도 (true반환)
        //      > 가입회원이 아닌 경우 비밀번호 변경 실패 (false반환)
        if(!userService.modifyUserPwd(id,pwd)) {
            response.put("result","fail");
        }else {
            response.put("result","success");
        }
        return response;
    }

}
