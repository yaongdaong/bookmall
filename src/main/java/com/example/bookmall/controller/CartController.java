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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping
    public String showCart(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userService.findByUsername(username);
        List<CartItem> items = cartService.getCartItemsByUser(user);
        int totalPrice = 0;
        for (CartItem cartItem : items) {
            totalPrice += cartItem.getBook().getUnit_price() * cartItem.getQuantity();
        }
        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("heading", "장바구니");
        model.addAttribute("subheading", "Your Cart");
        return "cart";
    }


    @PostMapping("/add/{id}")
    public String addItemToCart(@PathVariable Long id, @RequestParam int quantity, Principal principal) {
        // 사용자 정보 가져오기
        String username = principal.getName();
        Optional<User> user = userService.findByUsername(username);
        Book book = bookService.getBookById(id);
        cartService.addItemToCart(user, book, quantity);
        return "redirect:/cart"; // 장바구니 페이지로 리다이렉트
    }


    @PostMapping("/update/{id}")
    @ResponseBody
    public String modifyQuantity(@PathVariable Long id,@RequestParam Long bookId, @RequestParam int quantity){
        Optional<Cart> cartOptional = cartRepository.findById(id);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Book book = bookService.getBookById(bookId);
            cartService.modifyQuantity(cart, book, quantity);
            return "success";
        } else {
            return "error";
        }
    }

    //Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found for id: "+id));
        //
        //cartService.modifyQuantity(cart, book, quantity);
        //
        //if (cart != null) {
        //    Book book = null;
        //    for (CartItem cartItem : cart.getCartItems()) {
        //        if (cartItem.getBook() != null && cartItem.getBook().getId() == book.getId()) {
        //            book = cartItem.getBook();
        //            break;
        //        }
        //    }
        //    if (book != null) {
        //        cartService.modifyQuantity(cart, book, quantity);
        //        return "success";
        //    } else {
        //        System.out.println("에러: ID=" + id + ", Quantity=" + quantity + " (Book not found)");
        //        return "error";
        //    }
        //} else {
        //    System.out.println("에러: ID=" + id + ", Quantity=" + quantity + " (Cart not found)");
        //    return "error";
        //}
        //Cart cart = cartRepository.findById(id).orElse(null);
        //Book book = bookService.getBookById(id);
        //if (cart != null && book != null){
        //    cartService.modifyQuantity(cart, book, quantity);
        //    return "success";
        //} else {
        //    System.out.println("에러: ID=" + id + ", Quantity=" + quantity);
        //    return "error";
        //}

    @PostMapping("/delete/{id}")
    @ResponseBody
    public String deleteItem(@PathVariable Long id,@RequestParam Long bookId){
        Optional<Cart> cartOptional = cartRepository.findById(id);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Book book = bookService.getBookById(bookId);
            cartService.deleteItem(cart, book);
            return "success";
        } else {
            return "error";
        }
    }
    //public String updateCartItem(@RequestParam Long itemId, @RequestParam int newQuantity){
    //    CartItem cartItem = cartService.findCartItemById(itemId);
    //    if (cartItem != null){
    //        cartItem.setQuantity(newQuantity);
    //        cartService.updateCartItem(cartItem);
    //        return "success";
    //    } else {
    //        return "error";
    //    }
    //}

    //public String updateToCart(Cart cart, Book book, int quantity) {
    //    cartService.modifyQuantity(cart, book, quantity);
    //    return "redirect:/cart";
    //}

}
    //String username = principal.getName();
    //Optional<User> userOptional = userService.findByUsername(username);
    //
    //if(userOptional.isPresent()) {
    //    User user = userOptional.get();
    //    Cart cart = user.getCart();
    //    if (cart != null){
    //        List<CartItem> cartItems = cart.getCartItems();
    //        //List<CartItem> cartItems = cartService.getCartItemsByUser(user);
    //        model.addAttribute("cartItems",cartItems);
    //
    //        logger.debug("Cart items: {}",cartItems);
    //        System.out.println("cartItems"+cartItems);
    //        // 총 가격 정보를 뷰로 전달
    //        int total_price = cart.getTotal_price();
    //
    //        model.addAttribute("totalPrice",total_price);
    //        logger.debug("Total price: {}", total_price);
    //        System.out.println(" total_price"+ total_price);
    //    }
    //}

    //@GetMapping
    //public String cart(Model model, Principal principal) {
    //    System.out.println("cart method called");
    //    // 현재 로그인한 사용자의 정보 가져오기
    //    String username = principal.getName();
    //    Optional<User> userOptional = userService.findByUsername(username);
    //
    //    if (userOptional.isPresent()) {
    //        User user = userOptional.get();
    //        // 사용자의 장바구니 정보 가져오기
    //        Cart cart = user.getCart();
    //        if (cart != null) {
    //            // 장바구니 아이템 목록 가져오기
    //            List<CartItem> cartItems = cart.getCartItems();
    //
    //            // 모델에 데이터 추가
    //            model.addAttribute("cartItems", cart.getCartItems());
    //            model.addAttribute("totalPrice", cart.getTotal_price());
    //
    //        }
    //    }
    //
    //    return "cart";
    //}
    //@GetMapping("/items")
    //public String viewCart(Model model, Authentication authentication) {
    //    if (authentication != null && authentication.isAuthenticated()) {
    //        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    //        Optional<User> user = userService.findByUsername(userDetails.getUsername());
    //
    //        List<CartItem> cartItems = cartService.getCartItemsByUser(user);
    //        model.addAttribute("cartItems", cartItems);
    //
    //        int total = cartService.updateTotal_price();
    //        model.addAttribute("totalPrice", total);
    //    }
    //
    //    return "cart";
    //}
    //@PostMapping("/cart/delete")
    //public String deleteToCart(Cart cart){
    //    cartService.deleteCart(cart.getId());
    //    return "redirect:/cart";
    //}

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


