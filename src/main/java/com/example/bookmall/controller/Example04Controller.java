package com.example.bookmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;

@Controller
public class Example04Controller {
    // 매트릭스 변수: 웹에서 들어오는 웹 요청 URL에 포함된 다중 파라미터 값을 전달받는 변수
    // 매트릭스 변수는 웹 요청 URL의 어느 곳에나 위치할 수 있으므로 어떤 매트릭스 변수를 어떤 요소에 매핑할지 결정하려면
    // @MatrixVariable의 value와 pathVar속성을 사용해야 한다.
    // 웹요청 URL에 매트릭스 변수의 포함 여부와 상관없이 @MatrixVariable의 required 속성 값을 false로 설정하여
    // 선택 사항으로 사용할 수 있고, defaultValue 속성을 사용하여 기본값을 설정할 수도 있다.
    @GetMapping("/exam04/{bookId}/category/{category}")
    public String requestMethod(
            @MatrixVariable(value="publisher",pathVar = "bookId") String q1,
            @MatrixVariable(value="publisher",pathVar = "category") String q2,
            Model model){
            System.out.println("출판사1 : "+q1);
            System.out.println("출판사2 : "+q2);
            model.addAttribute("data","출판사1 : "+q1+"<br>"+"출판사2 : "+q2);
            return "webpage06";
    }
}
