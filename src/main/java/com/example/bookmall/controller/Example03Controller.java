package com.example.bookmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class Example03Controller {

    @GetMapping("/exam03")
    public String showFrom() {
        return "webpage07_03";
    }

    @ModelAttribute("title")
    public String setTitle(){
        return "@ModelAttribute 유형";
    }

    @ModelAttribute
    public void setsubTitle(Model model){
        model.addAttribute("subtitle","메소드에 @ModelAttribute 애노테이션 적용하기");
    }
}
