package com.example.bookmall.repository;

import com.example.bookmall.domain.Order;

public interface OrderRepository {
    Long saveOrder(Order order);
}
