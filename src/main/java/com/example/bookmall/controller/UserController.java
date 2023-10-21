package com.example.bookmall.controller;

import com.example.bookmall.domain.User;
import com.example.bookmall.repository.UserRepository;
import com.example.bookmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("heading","회원가입");
        model.addAttribute("subheading","Join");
        return "join";
    }

    @PostMapping("/join")
    public String join(User user,Model model){
        String username = user.getUsername();

        // 중복 아이디 체크
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("duplicateUsernameError", "이미 존재하는 아이디입니다.");
            return "join";
        }

        userService.join(user);
        return "redirect:/";
    }

    // @PostMapping("/doubleIdChk")
    // @ResponseBody
    // public String doubleIdChk(@RequestParam("username") String username){
    //     int result = userService.doubleIdCheck(username);
    //     return "result";
    // }

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
