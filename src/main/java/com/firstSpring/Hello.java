package com.firstSpring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller  // 1. 원격 호출 가능한 프로그램으로 등록
public class Hello {
    // 2. URl과 메서드 연결(맵핑 ,mapping)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
