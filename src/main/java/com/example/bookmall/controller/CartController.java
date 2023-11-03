package com.example.bookmall.controller;

import com.example.bookmall.domain.Book;
import com.example.bookmall.domain.Cart;
import com.example.bookmall.domain.CartItem;
import com.example.bookmall.domain.User;
import com.example.bookmall.repository.CartRepository;
import com.example.bookmall.service.BookService;
import com.example.bookmall.service.CartService;
import com.example.bookmall.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private CartRepository cartRepository;

    @GetMapping
    public String cart(Model model, Principal principal) {
        System.out.println("cart method called");
        // 현재 로그인한 사용자의 정보 가져오기
        String username = principal.getName();
        Optional<User> userOptional = userService.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 사용자의 장바구니 정보 가져오기
            Cart cart = user.getCart();
            if (cart != null) {
                // 장바구니 아이템 목록 가져오기
                List<CartItem> cartItems = cart.getCartItems();

                // 모델에 데이터 추가
                model.addAttribute("cartItems", cartItems);
                model.addAttribute("totalPrice", cart.getTotal_price());

            }
        }

        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addItemToCart(@PathVariable Long id, @RequestParam int quantity, Principal principal) {
        // 사용자 정보 가져오기
        String username = principal.getName();
        Optional<User> user = userService.findByUsername(username);
        Book book = bookService.getBookById(id);
        cartService.addItemToCart(user, book, quantity);
        return "redirect/cart"; // 장바구니 페이지로 리다이렉트
    }

    //@GetMapping("/clear")
    //public String clearCart(Principal principal) {
    //    // 사용자 정보 가져오기
    //    String username = principal.getName();
    //    Optional<User> userOptional = userService.findByUsername(username);
    //    if (userOptional.isPresent()) {
    //        User user = userOptional.get();
    //        // 장바구니 비우기
    //        cartService.clearCart(user.getCart());
    //    }
    //    return "redirect:/cart"; //장바구니 페이지로 리다이렉트
    //}

    //@PostMapping("/remove/{id}")
    //public ResponseEntity<String> removeItemFromCart(
    //        @RequestParam("cartId") Long cartId,
    //        @RequestParam("bookId") Long bookId,
    //        @RequestParam("quantity") int quantity
    //) {
    //    try {
    //        // CartService를 사용하여 아이템을 장바구니에서 제거
    //        Cart cart = cartService.getCartById(cartId);
    //        Book book = cartService.getBookById(bookId);
    //        cartService.removeItemFromCart(cart, book, quantity);
    //        return ResponseEntity.ok("Item removed from cart successfully.");
    //    } catch (Exception e) {
    //        return ResponseEntity.badRequest().body("Failed to remove item from cart: " + e.getMessage());
    //    }
    //}


}