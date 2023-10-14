package com.example.bookmall.controller.example;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Controller
public class Example09Controller {
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/json")
    public String showForm(){
        return "webpage14_02";
    }
    @PostMapping("/test")
    public void test(@RequestBody HashMap<String, Object> map){
        System.out.println(map);
    }

    @PostMapping("/example")
    public ResponseEntity<String> handleRequest(@RequestBody HashMap<String, Object> requestMap) {
        // requestMap을 사용하여 원하는 작업 수행
        // 예를 들어, requestMap에서 데이터 추출 또는 조작 가능
        // 반환 ResponseEntity는 HTTP 응답을 정의하는 데 사용
        return ResponseEntity.ok("Request received with data: " + requestMap.toString());
    }

}
