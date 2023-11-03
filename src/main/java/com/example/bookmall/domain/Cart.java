package com.example.bookmall.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {
        this.user = user;
    }

    private int total_price;

    public CartItem getCartItemByBook(Book book) {
        if (this.cartItems != null) {
            return this.cartItems.stream()
                    .filter(cartItem -> cartItem.getBook().equals(book))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
