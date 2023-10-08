package com.example.bookmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/exam08")
public class Example08Controller {

    @GetMapping
    public String showForm() {
        return "webpage14_01";
    }

    @PostMapping
    public String submit(@RequestParam("name") String name,
                         @RequestParam("age") int age,
                         @RequestParam("email") String email,
                         Model model) {
        model.addAttribute("title", "폼 데이터 받기");
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("email", email);
        return "webpage14_result";
    }
}

// @Controller
// @RequestMapping("/exam08")
// public class Example08Controller {
//     @GetMapping
//     public String showForm(){
//         return "webpage14_01";
//     }
//     @PostMapping
//     public String submit(@RequestBody String param, Model model){
//         model.addAttribute("title","@RequestBody로 정보 받기");
//         model.addAttribute("result",param);
//         return "webpage14_result";
//     }
//
// }
