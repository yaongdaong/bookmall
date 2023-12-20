package com.example.bookmall.controller;

import com.example.bookmall.domain.Book;
import com.example.bookmall.domain.CartItem;
import com.example.bookmall.domain.Order;
import com.example.bookmall.domain.User;
import com.example.bookmall.service.BookService;
import com.example.bookmall.service.CartService;
import com.example.bookmall.service.OrderService;
import com.example.bookmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showOrder(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username).orElse(null);

        List<CartItem> cartItems = cartService.getCartItemsByUser(Optional.ofNullable(user));
        model.addAttribute("user", user);
        model.addAttribute("cartItems", cartItems);
        return "order";
    }

    @PostMapping("/fromBook/{userId}")
    public String bookOrder(@RequestParam Long bookId, @RequestParam int quantity) {
        //Optional<User> userOptional = userService.getUserById(userId);

        //if (userOptional.isPresent()) {
        //    User user = userOptional.get();
        Book book = bookService.getBookById(bookId);
        Order order = orderService.createOrderFromBook(bookId, quantity);
        return "redirect:/order/confirmation/" + order.getId();

        //} else {
        //    return "redirect:/error";
        //}
    }

    @PostMapping("/fromCart")
    public String cartOrder(@RequestParam("user") User user, @RequestParam("bookIds") List<Long> bookIds, @RequestParam("quantities") List<Integer> quantities) {
        Map<Long, Integer> cartItems = new HashMap<>();
        for (int i = 0; i < bookIds.size(); i++) {
            cartItems.put(bookIds.get(i), quantities.get(1));
        }
        //Optional<User> userOptional = userService.getUserById(userId);

        //if (userOptional.isPresent()) {
        //    User user = userOptional.get();
        //    List<CartItem> cartItems = cartService.getCartItemsByUser(userOptional);
        Order order = orderService.createOrderFromCart(user, cartItems);
        //cartService.clearCart(userOptional.get());
        return "redirect:/order";
        //return "redirect:/order/confirmation/" + order.getId();
        //} else {
        //    return "redirect:/error";
        //}

    }


}
//@RequestMapping("/order/ISBN1234/2")
//public String process() {
//    orderService.confirmOrder(Integer.valueOf("ISBN1234"), 2);
//    return "redirect:/books";
//}

