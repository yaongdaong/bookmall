package com.example.bookmall.service;


import com.example.bookmall.domain.Book;
import com.example.bookmall.domain.Cart;
import com.example.bookmall.domain.CartItem;
import com.example.bookmall.domain.User;
import com.example.bookmall.repository.CartItemRepository;
import com.example.bookmall.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // 장바구니 생성 및 아이템 추가
    public void addItemToCart(Optional<User> userOptional, Book book, int quantity) {
        // 유저의 장바구니를 가져오거나 생성
        userOptional.ifPresent(user -> {
            Cart cart = user.getCart();
            if (cart == null) {
                cart = new Cart(user);
            }

            // 장바구니에 동일한 책이 이미 있는지 확인

            CartItem existingItem = cart.getCartItems().stream()
                    .filter(item -> item.getBook().getId() == book.getId())
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                // 동일한 책이 이미 장바구니에 있으면 수량을 증가
                existingItem.setQuantity((existingItem.getQuantity() + quantity));
            } else {
                // 새로운 아이템을 장바구니에 추가
                CartItem newItem = new CartItem(book, quantity);
                cart.getCartItems().add(newItem);
            }

            // cart_id를 cart_item에 추가
            for (CartItem cartItem : cart.getCartItems()){
                cartItem.setCart(cart);
            }
            // 주문할 가격 업데이트 (가격은 책의 가격에 수량을 곱한 값
            updateTotalPrice(cart);

            // 장바구니를 데이터베이스에 저장
            cartRepository.save(cart);
            cartItemRepository.saveAll(cart.getCartItems());
        });
        }


    // 아이템을 장바구니에서 제거
    public void removeItemFromCart(User user, Book book, int quantity) {
        Cart cart = user.getCart();
        if (cart != null) {
            CartItem existingItem = cart.getCartItems().stream()
                    .filter(item -> item.getBook().getId() == book.getId())
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                if (existingItem.getQuantity() > quantity) {
                    // 수량을 감소
                    existingItem.setQuantity(existingItem.getQuantity() - quantity);
                } else {
                    // 수량이 0이면 아이템을 장바구니에서 제거
                    cart.getCartItems().remove(existingItem);
                }
                // 주문할 가격 업데이트
                updateTotalPrice(cart);

                // 장바구니를 데이터베이스에 저장
            }
        }
    }


    // 장바구니의 총 가격 업데이트
    private void updateTotalPrice(Cart cart) {
        int total = 0;
        for (CartItem item : cart.getCartItems()) {
            total += item.getBook().getUnit_price() * item.getQuantity();
        }
        cart.setTotal_price(total);
    }

    // 장바구니 삭제
    public void clearCart(User user) {
        // 사용자의 장바구니를 비우는 로직 추가
        Cart cart = user.getCart();
        if (cart != null) {
            cart.getCartItems().clear();
            cart.setTotal_price(0);
            cartRepository.save(cart);
        }

    }

}