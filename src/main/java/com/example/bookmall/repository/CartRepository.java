package com.example.bookmall.repository;

import com.example.bookmall.domain.Cart;

// 장바구니 정보를 관리하는 퍼시스턴스 계층
public interface CartRepository {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
    void delete(String cartId);
}
