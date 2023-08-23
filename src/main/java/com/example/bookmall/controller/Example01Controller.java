package com.example.bookmall.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
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
}
