package com.firstSpring.controller.board;


import com.firstSpring.domain.board.FaqDto;
import com.firstSpring.service.board.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/faq")
public class FaqController {
    @Autowired
    FaqService faqService;
    //Rest API 디자인 예정
    //HTTP Method(GET, POST,PUT,DELETE)
    @GetMapping("/get")
    public ResponseEntity<?> get(String keyword) {
        // 응답 헤더에 UTF-8 인코딩을 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Map map = new HashMap();
        map.put("keyword",keyword);
        List<FaqDto> list = null;
        try {
            list = faqService.getChatSearch(map);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(list, headers,HttpStatus.OK);
    }
    @GetMapping("/get2")
    public ResponseEntity<Map<String,Object>> get2(String keyword) {
        // 응답 헤더에 UTF-8 인코딩을 설정
        Map<String, Object> response = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Map map = new HashMap();
        map.put("keyword",keyword);
        List<FaqDto> list = null;
        String url ="http://localhost:8080/notice/read?page=1&pageSize=10&option_key=&option_date=&keyword=&bno=27";
        try {
            list = faqService.getChatSearch(map);
            response.put("list",list);
            response.put("url",url);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(response, headers,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> test(){
        String test = "RestApiTest";
        return new ResponseEntity<>(test,HttpStatus.OK);
    }

}
