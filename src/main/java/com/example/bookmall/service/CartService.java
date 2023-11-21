package com.example.bookmall.service;


import com.example.bookmall.domain.Book;
import com.example.bookmall.domain.Cart;
import com.example.bookmall.domain.CartItem;
import com.example.bookmall.domain.User;
import com.example.bookmall.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    //@Autowired
    //private UserService userService;
    //
    //@Autowired
    //private BookService bookService;
    //@Autowired
    //private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public CartItem getCartItemByBook(Cart cart, Book book) {
        return cart.getCartItemByBook(book);
    }

    public void addItemToCart(Optional<User> userOptional, Book book, int quantity) {
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Cart cart = getCartByUser(user);
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                cartRepository.save(cart);
            }

            CartItem existingItem = getCartItemByBook(cart, book);
            if (existingItem != null) {
                existingItem.setQuantity((existingItem.getQuantity() + quantity));
            } else {
                CartItem newItem = new CartItem(book, quantity);
                newItem.setCart(cart);
                cart.getCartItems().add(newItem);
            }

            updateTotal_price(cart);
            cartRepository.save(cart);
        }
    }


        //int total = cart.getCartItems().stream()
        //        .mapToInt(item -> item.getBook().getUnit_price() * item.getQuantity())
        //        .sum();
        //cart.setTotal_price(total);


    //public List<CartItem> userCartView(Cart cart){
    //        List<Cart> cartItems = cartRepository.findAll();
    //
    //}
    public List<CartItem> getCartItemsByUser(Optional<User> userOptional) {
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Cart cart = getCartByUser(user);
            if (cart != null) {
                return cart.getCartItems();
            }

        }
        return new ArrayList<>();
    }

    // 장바구니의 총 가격 업데이트
    public int updateTotal_price(Cart cart) {
        int total = 0;

        for (CartItem item : cart.getCartItems()) {
            total += item.getBook().getUnit_price() * item.getQuantity();
        }

        cart.setTotal_price(total);
        return total;
    }

    public void modifyQuantity(Cart cart, Book book, int quantity) {
        // 아이템을 장바구니에서 제거 로직 추가
        CartItem existingItem = cart.getCartItemByBook(book);
        existingItem.setQuantity(quantity);
        // 주문할 가격 업데이트
        updateTotal_price(cart);
        // 장바구니를 데이터베이스에 저장
        cartRepository.save(cart);
    }
        //if (existingItem != null) {
        //    if (existingItem.getQuantity() > quantity) {
        //        // 수량을 감소
        //        existingItem.setQuantity(existingItem.getQuantity() - quantity);
        //    } else {
        //        // 수량이 0이면 아이템을 장바구니에서 제거
        //        cart.getCartItems().remove(existingItem);
        //    }
        //    // 주문할 가격 업데이트
        //    updateTotal_price(cart);
        //
        //    // 장바구니를 데이터베이스에 저장
        //    cartRepository.save(cart);
        //}

    public void deleteItem(Cart cart, Book book) {
        // 아이템을 장바구니에서 제거 로직 추가
        CartItem existingItem = cart.getCartItemByBook(book);
        cart.getCartItems().remove(existingItem);
        // 주문할 가격 업데이트
        updateTotal_price(cart);
        // 장바구니를 데이터베이스에 저장
        cartRepository.save(cart);
        //if (existingItem != null) {
        //    if (existingItem.getQuantity() > quantity) {
        //        // 수량을 감소
        //        existingItem.setQuantity(existingItem.getQuantity() - quantity);
        //    } else {
        //        // 수량이 0이면 아이템을 장바구니에서 제거
        //        cart.getCartItems().remove(existingItem);
        //    }
        //    // 주문할 가격 업데이트
        //    updateTotal_price(cart);
        //
        //    // 장바구니를 데이터베이스에 저장
        //    cartRepository.save(cart);
        //}
    }

    //@Transactional
    //public Cart downCart(Long cartId){
    //    Cart cart = cartRepository.findById(cartId).orElseThrow(()->{
    //        return new CustomException("찾을 수 없는 장바구니 입니다.");
    //    });
    //
    //    //수량 0개가 아닐때 감소시킴.
    //    if(cart. != 0) {
    //        int prev = cart.getCartItems();
    //        cart.setProduct_count(prev - 1);
    //
    //        //계산을 해주고 commit해야함.
    //        cart.calculateTotalPrice();
    //        cartRepository.save(cart);
    //    }
    //    return cart;
    //}
    //
    //public Cart getCartById(Long cartId) {
    //    return cartRepository.findById(cartId)
    //            .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
    //}

    //    public Cart createCart(User user){
        //        Cart cart = new Cart();
        //        cart.setUser(user);
        //        return cart;
        //    }
        //
        //
        //    // 아이템 추가
        //    public void addItemToCart(String username, Long bookId, int quantity) {
        //        Optional<User> userOptional = userService.findByUsername(username);
        //        if (userOptional.isPresent()) {
        //            User user = userOptional.get();
        //            // 책 정보 가져오기
        //            Book book = bookService.getBookById(bookId);
        //            // 사용자의 장바구니 확인
        //            Cart cart = user.getCart();
        //            if (cart == null) {
        //                // 장바구니가 없으면 새로 생성
        //                cart = createCart(user);
        //                user.setCart(cart);
        //            }
        //            // 동일한 책이 이미 장바구니에 있는지 확인
        //            CartItem existingItem = cart.getCartItemByBook(book);
        //            if (existingItem != null) {
        //                // 동일한 책이 이미 장바구니에 있으면 수량을 증가
        //                existingItem.setQuantity(existingItem.getQuantity() + quantity);
        //            } else {
        //                // 새로운 아이템을 장바구니에 추가
        //                CartItem newItem = new CartItem(book, quantity);
        //                newItem.setCart(cart);
        //                cart.getCartItems().add(newItem);
        //            }
        //            // 주문할 가격 업데이트( 가격은 책의 가격에 수량을 곱한 값)
        //            updateTotal_price(cart);
        //            // 장바구니를 데이터베이스에 저장
        //            cartRepository.save(cart);
        //        }
        //
        //    }
        //
        //
        //
        //    // 아이템을 장바구니에서 제거
        //    public void removeItemFromCart(Cart cart, Book book, int quantity) {
        //        // 아이템을 장바구니에서 제거 로직 추가
        //        CartItem existingItem = cart.getCartItemByBook(book);
        //        if (existingItem != null) {
        //            if (existingItem.getQuantity() > quantity) {
        //                // 수량을 감소
        //                existingItem.setQuantity(existingItem.getQuantity() - quantity);
        //            } else {
        //                // 수량이 0이면 아이템을 장바구니에서 제거
        //                cart.getCartItems().remove(existingItem);
        //            }
        //            // 주문할 가격 업데이트
        //            updateTotal_price(cart);
        //
        //            // 장바구니를 데이터베이스에 저장
        //            cartRepository.save(cart);
        //        }
        //    }
        //
        //

        //
        //    // 장바구니 삭제
        //    public void clearCart(Cart cart) {
        //        // 사용자의 장바구니를 비우는 로직 추가
        //        cart.getCartItems().clear();
        //        cart.setTotal_price(0);
        //        cartRepository.save(cart);
        //    }
        //
        //}
        //
    }