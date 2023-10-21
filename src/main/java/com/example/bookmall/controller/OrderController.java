// package com.example.bookmall.controller;
//
// import com.example.bookmall.service.OrderService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
//
// @Controller
// public class OrderController {
//     @Autowired
//     private OrderService orderService;
//
//     @RequestMapping("/order/ISBN1234/2")
//     public String process() {
//         orderService.confirmOrder(Integer.valueOf("ISBN1234"), 2);
//         return "redirect:/books";
//     }
// }
