 package com.example.bookmall.service;

 import com.example.bookmall.domain.*;
 import com.example.bookmall.repository.BookRepository;
 import com.example.bookmall.repository.OrderRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.time.LocalDateTime;
 import java.util.List;
 import java.util.Map;

 @Service
 public class OrderService {

     @Autowired
     private OrderRepository orderRepository;

     @Autowired
     private BookRepository bookRepository;

     public double calculateOrderPrice(Book book, int quantity) {
         double unitPrice = book.getUnit_price();
         return unitPrice * quantity;
     }

     public double calculateCartTotal(List<Book> books, List<Integer> quantities) {
         double total = 0.0;
         for (int i = 0; i < books.size(); i++) {
             total += calculateOrderPrice(books.get(i), quantities.get(i));
         }
         return total;
     }
     public Order createOrderFromBook(Long bookId, int quantity){
         Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not Found"));

         Order order = new Order();
         OrderItem orderItem = new OrderItem();
         orderItem.setBook(book);
         orderItem.setQuantity(quantity);
         orderItem.setOrder(order);
         order.getOrderItems().add(orderItem);

         return orderRepository.save(order);
     }

     public Order createOrderFromCart(User user,Map<Long, Integer> cartItems) {
         Order order = new Order();
         order.setUser(user);

         for (Map.Entry<Long, Integer> entry : cartItems.entrySet()) {
             Long bookId = entry.getKey();
             Integer quantity = entry.getValue();

             //Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));

             OrderItem orderItem = new OrderItem();
             //orderItem.setBook(book);
             //orderItem.setQuantity(quantity);
             //orderItem.setOrder(order);
             //order.getOrderItems().add(orderItem);
         }
         order.setOrder_date(LocalDateTime.now());
         return orderRepository.save(order);
     }
 }
