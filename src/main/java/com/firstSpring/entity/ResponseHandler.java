package com.firstSpring.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseHandler {

    // response에 정보를 담을 map
    private final static Map<String, Object> response = new HashMap<>();

    // 성공 처리 메서드
    public ResponseEntity<Map<String, Object>> successHandler(String message) {
        response.put("result", "success");
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // 응답에 추가 데이터 넣기 위한 메서드
    public void add(String key,Object value) {
        response.put(key,value);
    }
}
