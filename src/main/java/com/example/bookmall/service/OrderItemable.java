package com.example.bookmall.service;

import com.example.bookmall.domain.Book;

import java.math.BigDecimal;

public interface OrderItemable {
    Book getBook();
    int getQuantity();
    int getOrderPrice();
}
