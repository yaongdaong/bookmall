package com.example.bookmall.controller.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;

@Controller
public class Example06Controller {
    @GetMapping("/exam06/{bookId}/category/{category}")
    public String requestMethod(
            // 웹 요청 URL의 어느 위치에나 포함될 수 있는 매트릭스 변수를 MultiValueMap 객체에 저장하여 처리
            @MatrixVariable MultiValueMap<String, String> matrixVars,
            @MatrixVariable(pathVar="category")MultiValueMap<String,String>matrixVars2, Model model){
        System.out.println(matrixVars);
        System.out.println(matrixVars2);
        model.addAttribute("data",matrixVars + "<br>"+matrixVars2);
        return "webpage06";
    }
}
