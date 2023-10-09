package com.example.bookmall.service;

import com.example.bookmall.domain.Cart;
import com.example.bookmall.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    // 1. crate() 메서드는 장바구니 저장소 객체에서 생성한 장바구니를 가져와 반환
    public Cart create(Cart cart){
        return cartRepository.create(cart);
    }

    // 2. read() 메서드는 저장소 객체에서 장바구니 ID에 대해 장바구니에 등록된 모든 정보를 가져와 반환
    public Cart read(String cartId){
        return cartRepository.read(cartId);
    }
    public void update(String cartId, Cart cart){
        cartRepository.update(cartId, cart);
    }
}
