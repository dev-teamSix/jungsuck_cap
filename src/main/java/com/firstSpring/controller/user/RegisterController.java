package com.firstSpring.controller.user;

import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;
import com.firstSpring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    UserService userService;


    @GetMapping("/register")
    @LogException
    public String registerPage() {
        return "register";
    }

    @PostMapping("/save")
    @LogException
    public String savaUserProcess(UserDto userDto) {
        userService.saveCustJoinInfo(userDto);
        return "home";
    }
}
