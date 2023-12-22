package com.example.bookmall.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

@Table(name = "cartitem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;


    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{id=" + id + ", quantity=" + quantity + "}";
    }


}