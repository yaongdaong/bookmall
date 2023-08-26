package com.example.bookmall.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.bookmall.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Example01Controller {

    @GetMapping("/exam01/{bookId}")
    public String requestMethod(@PathVariable String bookId, Model model){
        System.out.println("도서 ID : "+bookId);
        model.addAttribute("data", "도서 ID : "+bookId);
        return "webpage06";
    }

    @GetMapping("/member")
    public String showForm(Model model){
        Member member = new Member();
        System.out.println("@GetMapping-------------");
        System.out.println("아이디: " + member.getId());
        System.out.println("비밀번호: " + member.getPassword());
        System.out.println("거주지: " + member.getCity());
        System.out.println("성별: " + member.getSex());
        System.out.println("취미: " + member.getHobby());
        model.addAttribute("member",member);
        return "webpage07_01";
    }

}
