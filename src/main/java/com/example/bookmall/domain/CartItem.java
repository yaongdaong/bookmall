package com.example.bookmall.domain;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {
    private static final long getSerialVersionUID = 3636831123198280235L;
    private Book book; // 도서
    private int quantity; // 도서 개수
    private int totalPrice; // 도서 가격

    // 1. generate constructors from superclass-object
    public CartItem(){
    }
    // 2.generate constructor using fields
    public CartItem(Book book){
        super();
        this.book = book;
        this.quantity = 1;
        this.totalPrice = book.getUnitPrice();
    }

    // 3.generate getters and setters
    public Book getBook(){
        return book;
    }

    public void setBook(Book book){
        this.book = book;
        this.updateTotalPrice();
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
        this.updateTotalPrice();
    }

    public int getTotalPrice(){
        return totalPrice;
    }

    // 4.updateTotalPrice()메서드는 장바구니에 등록되는 도서별 총액을 산출
    public void updateTotalPrice(){
        totalPrice = this.book.getUnitPrice() * this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(book, cartItem.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }
}
