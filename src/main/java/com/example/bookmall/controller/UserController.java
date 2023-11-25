package com.example.bookmall.controller;

import com.example.bookmall.domain.User;
import com.example.bookmall.repository.UserRepository;
import com.example.bookmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("heading", "회원가입");
        model.addAttribute("subheading", "Join");
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("user") User user) {
        // 비밀번호 확인
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "redirect:/join?error";
        }

        // 비밀번호 일치하는 경우 사용자 등록
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       userService.join(user);
       return "redirect:/login";
    }

    @GetMapping("/check-username")
    public boolean checkUsername(@RequestParam String username) {
        // 아이디 중복 확인
        return !userService.isUsernameTaken(username);
    }
    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("heading", "Welcome to BookMarket");
        model.addAttribute("subheading", "Welcome to Web Shopping Mall!");
        return "hello";

    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("heading", "로그인");
        model.addAttribute("subheading", "Login");
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }


    @GetMapping("/admin")
    //@Secured("ADMIN")
    public String listUsers(Model model, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        model.addAttribute("authorities",authorities);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("heading", "회원 목록");
        model.addAttribute("subheading", "Users List");
        return "admin";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id).orElse(null);
        model.addAttribute("user", user);
        return "admin";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        if (user != null) {
            return "redirect:/users";
        }
        return "redirect:/edit/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user/my-page")
    public String myPage(){
        return "my_page";
    }
}
