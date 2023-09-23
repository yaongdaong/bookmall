package com.example.bookmall.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Product {
    @NotNull
    @Size(min=4, max=10, message="4~10자 이내로 입력해 주세요")
    private String name;

    @Min(value=0)
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
