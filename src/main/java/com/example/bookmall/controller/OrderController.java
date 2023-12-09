 package com.example.bookmall.controller;

 import com.example.bookmall.domain.CartItem;
 import com.example.bookmall.domain.Order;
 import com.example.bookmall.domain.User;
 import com.example.bookmall.service.CartService;
 import com.example.bookmall.service.OrderService;
 import com.example.bookmall.service.UserService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestMapping;

 import java.security.Principal;
 import java.util.List;
 import java.util.Optional;

 @Controller
 @RequestMapping("/order")
 public class OrderController {
     @Autowired
     private OrderService orderService;

     @Autowired
     private CartService cartService;

     @Autowired
     private UserService userService;

     @GetMapping
     public String showOrder(Model model, Principal principal){
         String username = principal.getName();
         User user = userService.findByUsername(username).orElse(null);

         List<CartItem> cartItems = cartService.getCartItemsByUser(Optional.ofNullable(user));
         model.addAttribute("user",user);
         model.addAttribute("cartItems",cartItems);
         return "order";
     }

     @PostMapping("/fromCart/{id}")
     public String cartOrder(Long userId){
         Optional<User> userOptional = userService.getUserById(userId);
         List<CartItem> cartItems = cartService.getCartItemsByUser(userOptional);
         Order order = orderService.createOrder(cartItems);
         cartService.clearCart(userOptional.get());
         return "redirect:/order/confirmation/"+order.getId();
     }

     @PostMapping("/fromBook/{id}")
     public String bookOrder(@PathVariable Long userId){
         Optional<User> userOptional = userService.getUserById(userId);
         List<CartItem> cartItems = cartService.getCartItemsByUser(userOptional);
         Order order = orderService.createOrder(cartItems);
         cartService.clearCart(userOptional.get());
         return "redirect:/order/confirmation/"+order.getId();
     }



     //@RequestMapping("/order/ISBN1234/2")
     //public String process() {
     //    orderService.confirmOrder(Integer.valueOf("ISBN1234"), 2);
     //    return "redirect:/books";
     //}
 }
