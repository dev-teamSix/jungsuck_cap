package com.firstSpring.controller.chat;

import com.firstSpring.domain.user.UserDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/chat")
public class ChatController{

    @GetMapping("/init")
    public ResponseEntity<String> chatInit(HttpServletRequest request){
        System.out.println("init 호출");
        HttpSession session = request.getSession();
        System.out.println("session:"+session.getId());
        String response ="";
        // 응답 헤더에 UTF-8 인코딩을 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        if(!loginCheck(request)){
            System.out.println("if");
            //로그인 안했으면 로그인 하도록 유도
            response ="비회원 고객은 제한된 서비스만 이용 가능합니다." +
                    "링크를 통해 로그인 해주시기 바랍니다."+
                    "<a href=\"http://localhost:8080/login/form\">로그인</a>";
        }else{
            //로그인 했으면 OOO님 추가한 메시지 나오도록
            String id = ((UserDto) request.getSession().getAttribute("sessionUser")).getId();
            response="안녕하세요,"+id+"님 저는 모자의정석의 LLM 챗봇 [6ragon] 입니다. 무엇을 도와드릴까요?";

        }
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    private boolean loginCheck(HttpServletRequest request){
        HttpSession session = request.getSession();
        return session.getAttribute("sessionUser")!=null;
    }

    //서버 측 Accept 헤더 처리 테스트
    @GetMapping(value = "/data", produces = "application/json")
    public ResponseEntity<Map<String, String>> getJsonResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "This is a JSON response");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/data", produces = "application/xml")
    public ResponseEntity<String> getXmlResponse() {
        String xmlResponse = "<response><message>This is an XML response</message></response>";
        return new ResponseEntity<>(xmlResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/data", produces = "text/html")
    public ResponseEntity<String> getTextResponse() {
        String text = "<p>text TEST</p>";
        return new ResponseEntity<>(text, HttpStatus.OK);
    }

    //스프링 내 다른 api 호출하는 테스트 해봅시다.(Faq api호출)
    @GetMapping("/test")
    public String test() {
        System.out.println("test호출");
        return "testReturn";
    }
    @PostMapping("/PostTest")
    public String PostTest(){
        System.out.println("postTest");
        return "postTestReturn";
    }

    @GetMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(String user_input){

        System.out.println("user_input:"+user_input);
        StringBuilder result = new StringBuilder();

        // 응답 헤더에 UTF-8 인코딩을 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        try {
            //메세지 내용 중에 해당 내용이 있으면 실행
            if(user_input.equals("문의")){
                String keyword ="문의";
                String enKeyword =  URLEncoder.encode(keyword, StandardCharsets.UTF_8.toString());
                String url = "http://localhost:8080/faq/get2?keyword="+enKeyword;
                URL url2 = null;
                url2 = new URL(url);
                System.out.println(url2);
                HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
                urlConnection.setRequestMethod("GET");
                BufferedReader br2;

                br2 = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

                String returnLine ="";

                while ((returnLine = br2.readLine()) != null) {
                    result.append(returnLine + "\n\r");
                }

                urlConnection.disconnect();
                ResponseEntity<?> ttt2= new ResponseEntity<>(result.toString(),headers, HttpStatus.OK);
                System.out.println(2);
                return ttt2;

            }
            String encodedInput = URLEncoder.encode(user_input, StandardCharsets.UTF_8.toString());
            String urlStr = "http://127.0.0.1:5000/openAPI?user_input="+encodedInput;
            System.out.println(urlStr);
            URL url = null;
            url = new URL(urlStr);
            System.out.println(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br;
            System.out.println("t2");
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine ="";
            System.out.println("t3");
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n\r");
            }

            System.out.println("t33");
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
            System.out.println(result.toString());
           ResponseEntity<?> ttt= new ResponseEntity<>(result.toString(),headers, HttpStatus.OK);
        System.out.println(ttt);
        return ttt;
    }

    @GetMapping("/test2")
    public ResponseEntity<?> test2() {

        StringBuilder result = new StringBuilder();

        String urlStr = "http://127.0.0.1:5000/get";

        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine ="flaskTest11111111111";

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n\r");
            }

            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }
}
