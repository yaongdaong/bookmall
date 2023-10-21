// package com.example.bookmall.domain;
//
// import java.io.Serializable;
// import java.util.Objects;
//
// public class Order implements Serializable {
//     // 필드 선언
//     private static final long serialVersionUID = 2659461092139119863L;
//     private Long orderId;
//     private Cart cart;
//     private Customer customer;
//     private Shipping shipping;
//     // getter setter
//     public Long getOrderId() {
//         return orderId;
//     }
//
//     public void setOrderId(Long orderId) {
//         this.orderId = orderId;
//     }
//
//     public Cart getCart() {
//         return cart;
//     }
//
//     public void setCart(Cart cart) {
//         this.cart = cart;
//     }
//
//     public Customer getCustomer() {
//         return customer;
//     }
//
//     public void setCustomer(Customer customer) {
//         this.customer = customer;
//     }
//
//     public Shipping getShipping() {
//         return shipping;
//     }
//
//     public void setShipping(Shipping shipping) {
//         this.shipping = shipping;
//     }
//
//     // 생성자
//     public Order(Customer customer, Shipping shipping) {
//         this.customer = customer;
//         this.shipping = shipping;
//     }
//
//     // equals and hashCode
//     @Override
//     public boolean equals(Object o) {
//         if (this == o) return true;
//         if (o == null || getClass() != o.getClass()) return false;
//         Order order = (Order) o;
//         return Objects.equals(orderId, order.orderId);
//     }
//
//     @Override
//     public int hashCode() {
//         return Objects.hash(orderId);
//     }
// }
