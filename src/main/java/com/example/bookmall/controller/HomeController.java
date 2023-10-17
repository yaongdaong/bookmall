// package com.example.bookmall.controller;
//
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
//
// @Controller
// public class HomeController {
//     @RequestMapping(value="/",method = RequestMethod.GET)
//     public String welcome(Model model) {
//         model.addAttribute("heading","Welcome to BookMarket");
//         model.addAttribute("subheading","Welcome to Web Shopping Mall!");
//         return "hello";
//
//     }
//
//     @GetMapping("/login")
//     public String login(Model model){
//         model.addAttribute("heading","로그인");
//         model.addAttribute("subheading","Login");
//         return "login";
//     }
//
//     @GetMapping("/logout")
//     public String logout(){
//         return "login";
//     }
// }
