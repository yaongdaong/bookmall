// package com.example.bookmall.service;
//
// import com.example.bookmall.domain.Book;
// import com.example.bookmall.domain.Order;
// import com.example.bookmall.repository.BookRepository;
// import com.example.bookmall.repository.OrderRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// @Service
// public class OrderServiceImpl implements OrderService {
//     @Autowired
//     private BookRepository bookRepository;
//     @Autowired
//     private OrderRepository orderRepository;
//     @Autowired
//     private CartService cartService;
//
//     // confirmOrder() 메서드는 도서 재고 수에 대한 도서 주문 가능 여부를 처리.
//     // 주문 도서 수가 재고 수 보다 많으면 IllegalArgumentException 예외를 발생
//     public void confirmOrder(Integer id, long quantity) {
//         Book bookById = bookRepository.getBookById(id);
//         if (bookById.getUnitsInStock() < quantity) {
//             throw new IllegalArgumentException("품절입니다. 사용가능한 재고수 :" + bookById.getUnitsInStock());
//         }
//         bookById.setUnitsInStock(bookById.getUnitsInStock() - quantity);
//     }
//     // saveOrder() 메서드는 주문 내역에 대해 Order 저장소 객체에 저장하고, 현재 장바구니 정보를 삭제한 후 주문 내역 ID를 반환
//     public Long saveOrder(Order order) {
//         Long orderId = orderRepository.saveOrder(order);
//         cartService.delete(order.getCart().getCartId());
//         return orderId;
//     }
// }
