package com.firstSpring.service.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstSpring.dao.order.Chat2Dao;
import com.firstSpring.domain.order.Chat2Dto;
import com.firstSpring.domain.order.RequestSendToFlaskDto;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class Chat2Service {
    @Autowired
    private Chat2Dao chat2Dao;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public String sendToFlask(RequestSendToFlaskDto dto) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String param = objectMapper.writeValueAsString(dto);
        HttpEntity<String> entity = new HttpEntity(param, headers);
        String url = "http://127.0.0.1:8082/receive_string";
        return restTemplate.postForObject(url, entity, String.class);
    }

    public List<Chat2Dto> getMsgData(String cust_id) throws Exception {
        return chat2Dao.selectMsgData(cust_id);
    }

    public Integer getMsgNo() throws Exception {
        return chat2Dao.selectMsgNo();
    }
}