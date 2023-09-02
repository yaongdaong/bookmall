package com.example.bookmall.controller;

import com.example.bookmall.domain.Member;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;

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

    @PostMapping("/member")
    public String submitForm(@ModelAttribute Member member, Model model){
        System.out.println("@PostMapping-------------");
        System.out.println("아이디: " + member.getId());
        System.out.println("비밀번호: " + member.getPassword());
        System.out.println("거주지: " + member.getCity());
        System.out.println("성별: " + member.getSex());
        System.out.println("취미: ");
        for (int i = 0; i < member.getHobby().length; i++)
            System.out.println("["+ member.getHobby()[i]+"]");
        model.addAttribute("member",member);
        return "webpage07_02";
    }

    @GetMapping("/exam02")
    public String requestMethod(Model model){
        return "webpage08_02";
    }

    @GetMapping("/admin/tag")
    public String requestMethod2(Model model){
        return "webpage08_02";
    }

    @GetMapping("/form_1")
    public String requestForm(){
        return "webpage09_01";
    }

    @PostMapping("/form_1")
    public String submitForm(MultipartHttpServletRequest request){
        String name = request.getParameter("name");
        MultipartFile file = request.getFile("fileImage");
        String filename = file.getOriginalFilename();
        File f = new File("c:\\upload\\"+name+"_"+filename);
        try{
            file.transferTo(f);
        }catch(IOException e){
            e.printStackTrace();
        }
        return "webpage09_submit";
    }
    @SuppressWarnings("serial")
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="찾을 수 없습니다.")
    public class Example02Exception extends Exception{
        public Example02Exception(String message){
            super(message);
            System.out.print(message);
        }
    }

    @GetMapping("/exam02_1")
    public void handleRequest() throws Exception{
        throw new Exception(new Example02Exception("Example02Exception 메시지입니다."));
    }
}
