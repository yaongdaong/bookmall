package com.example.bookmall.service;

import com.example.bookmall.domain.Order;

public interface OrderService {
    void confirmOrder(String bookId, long quantity);
    Long saveOrder(Order order);
}
