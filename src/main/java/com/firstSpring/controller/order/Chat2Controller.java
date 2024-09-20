package com.firstSpring.controller.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.firstSpring.domain.order.Chat2Dto;
import com.firstSpring.domain.order.RequestSendToFlaskDto;
import com.firstSpring.domain.user.UserDto;
import com.firstSpring.service.order.Chat2Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/chat2"})
public class Chat2Controller {
    @Autowired
    Chat2Service chat2Service;

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("sessionUser") != null;
    }

    @PostMapping({"/sendchat"})
    public ResponseEntity<String> sendToFlask(@RequestBody RequestSendToFlaskDto dto, HttpServletRequest request) throws JsonProcessingException {
        try {
            String cust_id = "";
            if (this.loginCheck(request)) {
                cust_id = ((UserDto)request.getSession().getAttribute("sessionUser")).getId();
            } else {
                Integer msgNo = this.chat2Service.getMsgNo();
                int lastMsgNo;
                if (msgNo == null) {
                    lastMsgNo = 0;
                } else {
                    lastMsgNo = msgNo + 1;
                }

                cust_id = "guest" + lastMsgNo;
                UserDto userDto = new UserDto();
                userDto.setId(cust_id);
                HttpSession session = request.getSession();
                session.setAttribute("sessionUser", userDto);
            }

            dto.setCust_id(cust_id);
            String str = this.chat2Service.sendToFlask(dto);
            if (str.equals("로그인 해주세요.")) {
                return new ResponseEntity("권한이 없습니다.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity("success", HttpStatus.OK);
    }

    @GetMapping({"/showchat"})
    public String showPage(Model m, HttpServletRequest request) {
        String cust_id = "";
        if (this.loginCheck(request)) {
            cust_id = ((UserDto)request.getSession().getAttribute("sessionUser")).getId();
        }

        try {
            List<Chat2Dto> messages = this.chat2Service.getMsgData(cust_id);
            m.addAttribute("messages", messages);
            return "chat2";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}