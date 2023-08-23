package com.example.bookmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Example02Controller {

    //    경로 변수(path variables)는 웹 요청 URL에 포함된 파라미터 값을 전달받는 데 사용하는 변수
    //    이 때 매핑 경로를 설정하는 @RequestMapping에 중괄호를 사용하여 웹 요청 URL에 포함된 요청 조건 값을 전달받음
    //    중괄호 안에 명시된 것이 경로 변수이며, 하나 또는 그 이상의 경로 변수를 포함할 수 있다.
    @GetMapping("/exam02/{category}/publisher/{publisher}")
    //    웹 요청 URL에 포함된 파라미터 값들을 경로 변수 category와 publisher로 전달받고,
    //    이를 요청 처리 메서드 requestMethod()에서 동일한 경로 변수 이름으로 전달받습니다.
    public String requestMethod(@PathVariable String category, @PathVariable String publisher, Model model) {
        System.out.println("도서 분야 : " + category);
        System.out.println("출판사 : " + publisher);
        model.addAttribute("data", "도서 분야 : " + category + "<br>" + "출판사 : " + publisher);
        return "webpage06";
    }
}
