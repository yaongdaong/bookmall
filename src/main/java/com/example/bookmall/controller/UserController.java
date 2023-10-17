package com.example.bookmall.controller;

import com.example.bookmall.domain.User;
import com.example.bookmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("heading","회원가입");
        model.addAttribute("subheading","Join");
        return "join";
    }

    @PostMapping("/join")
    public String join(User user){
        userService.join(user);
        return "redirect:/";
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute("heading","Welcome to BookMarket");
        model.addAttribute("subheading","Welcome to Web Shopping Mall!");
        return "hello";

    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("heading","로그인");
        model.addAttribute("subheading","Login");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }

}
