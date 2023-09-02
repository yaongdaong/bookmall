package com.example.bookmall.controller;

import com.example.bookmall.domain.Member;
import com.example.bookmall.exception.Example03Exception;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class Example03Controller {

    @GetMapping("/exam03")
    public String showFrom() {
        return "webpage07_03";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "@ModelAttribute 유형";
    }

    @ModelAttribute
    public void setsubTitle(Model model) {
        model.addAttribute("subtitle", "메소드에 @ModelAttribute 애노테이션 적용하기");
    }

    @GetMapping("/exam03_1")
    public String requestMethod(Model model){
        return "webpage08_03";
    }

    @GetMapping("/admin/tag_1")
    public String requestMethod2(Model model){
        return "webpage08_3";
    }

    @GetMapping("/form_2")
    public String requestForm(Member member){
        return "webpage09_02";
    }

    @PostMapping("/form_2")
    public String submitForm(@ModelAttribute("member")Member member, HttpServletRequest request, HttpSession session){
        String name = member.getName();
        String filename = member.getImageFile().getOriginalFilename();
        try{
            member.getImageFile().transferTo(new File("c:\\upload\\"+name+"_"+filename));
        }catch(IOException e){
            e.printStackTrace();
        }
        return "webpage09_submit";
    }

    @GetMapping("/exam03_2")
    public void handleRequest(){
        throw new Example03Exception();
    }

    @ExceptionHandler(Example03Exception.class)
    public ModelAndView handleException(Example03Exception ex){
        ModelAndView model = new ModelAndView();
        model.addObject("errorMessage", ex.getErrMsg());
        model.addObject("exception",ex);
        model.setViewName("webpage10_03");
        return model;
    }
}