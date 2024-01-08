 package com.example.bookmall.service;

 import com.example.bookmall.domain.*;
 import com.example.bookmall.repository.BookRepository;
 import com.example.bookmall.repository.OrderRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.time.LocalDateTime;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;

 @Service
 public class OrderService {

     @Autowired
     private OrderRepository orderRepository;
@Autowired
private BookService bookService;
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

     //public Order createOrderFromCart(User user,List<CartItem> cartItems) {
     public Order createOrderFromCart(User user,Map<Long, Integer> cartItems) {
         Order order = new Order();
         order.setUser(user);
         List<OrderItem> orderItems = new ArrayList<>();
         for (Map.Entry<Long, Integer> entry : cartItems.entrySet()) {
             Long bookId = entry.getKey();
             Integer quantity = entry.getValue();

             // Assuming you have a method to retrieve a Book entity by its ID
             Book book = bookService.getBookById(bookId);

             OrderItem orderItem = OrderItem.createOrderItem(book, quantity);
             orderItems.add(orderItem);
         }

         //for (CartItem cartitem: cartItems){
         //    OrderItem orderItem = OrderItem.createOrderItem(cartitem.getBook(),cartitem.getQuantity());
         //    orderItems.add(orderItem);
         //}
         Order newOrder = Order.createOrder(user, orderItems);
         newOrder.setPrice(newOrder.getTotalPrice());
         return orderRepository.save(newOrder);
         //for (Map.Entry<Long, Integer> entry : cartItems.entrySet()) {
         //    Long bookId = entry.getKey();
         //    Integer quantity = entry.getValue();
         //
         //    Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
         //
         //    OrderItem orderItem = new OrderItem();
         //    orderItem.setBook(book);
         //    orderItem.setQuantity(quantity);
         //    orderItem.setOrder(order);
         //    order.getOrderItems().add(orderItem);
         //}
         //order.setOrder_date(LocalDateTime.now());
         //return orderRepository.save(order);
     }
 }
