package com.example.bookmall.domain;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {
    // 1. 필드 선언
    private static final long serialVersionUID = 3636831123198280235L;
    private String customerId;
    private String name;
    private Address address;
    private String phone;

    // 2. 생성자
    public Customer(Address address) {
        this.address = address;
    }
    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    // 3. getter setter
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
