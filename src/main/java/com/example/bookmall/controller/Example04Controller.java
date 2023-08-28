package com.example.bookmall.controller;

import com.example.bookmall.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class Example04Controller {
    // 매트릭스 변수: 웹에서 들어오는 웹 요청 URL에 포함된 다중 파라미터 값을 전달받는 변수
    // 매트릭스 변수는 웹 요청 URL의 어느 곳에나 위치할 수 있으므로 어떤 매트릭스 변수를 어떤 요소에 매핑할지 결정하려면
    // @MatrixVariable의 value와 pathVar속성을 사용해야 한다.

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

    @GetMapping("/exam04")
    public String showForm(Model model){
        model.addAttribute("member",new Member());
        return "webpage07_01";
    }

    @PostMapping("/exam04")
    public String submit(@ModelAttribute Member member, Model model){
        model.addAttribute("member",member);
        return "webpage07_02";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setAllowedFields("id","password","city","sex");
    }
}
