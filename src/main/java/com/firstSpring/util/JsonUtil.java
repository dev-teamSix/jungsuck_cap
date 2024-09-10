package com.firstSpring.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtil {
    //재사용을 위해 static
    private  static final ObjectMapper objectMapper = new ObjectMapper();

    //공통으로 사용할 JSON 변환 메서드
    public static <T> String covertListToJson(List<T> list) {
        try{
            return objectMapper.writeValueAsString(list);
        }catch (JsonProcessingException e){
            throw new RuntimeException("JSON 변환 중 오류가 발생했습니다.",e);
        }
    }

}
