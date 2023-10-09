package com.example.bookmall.service;

import com.example.bookmall.domain.Cart;

// 도서 장바구니 정보를 반환하는 서비스 계층 구현
public interface CartService {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
}
