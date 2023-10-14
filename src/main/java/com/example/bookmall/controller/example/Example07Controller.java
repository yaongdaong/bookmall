package com.example.bookmall.controller.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Example07Controller {
    @GetMapping("/exam07")
    // 요청 파라미터와 @RequestParam
    // 웹 요청 URL에 포함된 쿼리문을 처리하는 요청 처리 메서드의 파라미터에 사용하는 @RequestParam
    // 요청 파라미터는 일반적인 웹 서버 애플리케이션의 GET 방식의 쿼리문 '변수명=값' 형태로 데이터를 전송
    // 웹 요청 URL에 다중 쿼리문을 가지면 color=red&year=2019처럼 &로 구분하여 표현
    // @RequestParam은 컨트롤러의 요청 처리 메서드를 구현할 때 가장 많이 사용하는 애너테이션
    // 대체로 요청 파라미터의 수가 많지 않을 때 사용
    public String requestMethod(@RequestParam String id, Model model){
        System.out.println("도서 ID : "+id);
        model.addAttribute("data","도서 ID : "+id);
        return "webpage06";
    }
}
