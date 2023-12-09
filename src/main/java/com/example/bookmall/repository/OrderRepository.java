 package com.example.bookmall.repository;

 import com.example.bookmall.domain.Order;
 import org.springframework.data.jpa.repository.JpaRepository;

 public interface OrderRepository extends JpaRepository<Order, Long> {
     //Long saveOrder(Order order);
 }
