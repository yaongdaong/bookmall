package com.example.bookmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;

@Controller
public class Example05Controller {
    @GetMapping("/exam05/{bookId}")
    public String requestMethod(
            // 웹요청 URL에 매트릭스 변수의 포함 여부와 상관없이 @MatrixVariable의 required 속성 값을 false로 설정하여
            // 선택 사항으로 사용할 수 있고, defaultValue 속성을 사용하여 기본값을 설정할 수도 있다.
            @MatrixVariable(required = false, defaultValue = "길벗") String q,
            Model model) {
        System.out.println("출판사 : " + q);
        model.addAttribute("data", "출판사 : " + q);
        return "webpage06";
    }

}
