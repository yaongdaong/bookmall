package com.example.bookmall.controller;

import com.example.bookmall.domain.Book;
import com.example.bookmall.domain.User;
import com.example.bookmall.service.BookService;
import com.example.bookmall.service.CartService;
import com.example.bookmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping("/add/{id}")
    public String addItemToCart(@PathVariable Long id, @RequestParam int quantity, Principal principal) {
        // 사용자 정보 가져오기
        String username = principal.getName();
        Optional<User> user = userService.findByUsername(username);

        // 책 정보 가져오기
        Book book = bookService.getBookById(id);

        // 장바구니에 아이템 추가
        cartService.addItemToCart(user, book, quantity);


        return "redirect:/cart"; // 장바구니 페이지로 리다이렉트
    }

    @GetMapping("/remove/{id}")
    public String removeItemFromCart(@PathVariable Long id, @RequestParam int quantity, Principal principal) {
        // 사용자 정보 가져오기
        String username = principal.getName();
        Optional<User> userOptional = userService.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 책 정보 가져오기
            Book book = bookService.getBookById(id);

            // 장바구니에서 아이템 제거
            cartService.removeItemFromCart(user, book, quantity);
        }
        return "redirectL/cart"; // 장바구니 페이지로 리다이렉트
    }

    @GetMapping("/claer")
    public String clearCart(Principal principal) {
        // 사용자 정보 가져오기
        String username = principal.getName();
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 장바구니 비우기
            cartService.clearCart(user);
        }
        return "redirect:/cart"; //장바구니 페이지로 리다이렉트
    }


}