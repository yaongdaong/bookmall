 package com.example.bookmall.service;

 import com.example.bookmall.domain.CartItem;
 import com.example.bookmall.domain.Order;
 import com.example.bookmall.domain.OrderItem;
 import com.example.bookmall.domain.User;
 import com.example.bookmall.repository.OrderRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.List;

 @Service
 public class OrderService {

     @Autowired
     private OrderRepository orderRepository;

     public Order createOrder(User user, List<CartItem> cartItems){
         Order order = new Order();
         order.setUser(user);

         // 각 카트 아이템에 대한 처리
         for (CartItem cartItem : cartItems){
             OrderItem orderItem = new OrderItem();
             orderItem.setBook(cartItem.getBook());
             orderItem.setQuantity(cartItem.getQuantity());
             orderItem.setOrder_price(cartItem.getCart().getTotal_price());

             order.addOrderItem(orderItem);

         }
         return orderRepository.save(order);
     }
     //void confirmOrder(Integer id, long quantity);
     //Long saveOrder(Order order);
 }
