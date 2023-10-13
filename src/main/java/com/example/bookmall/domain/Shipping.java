package com.example.bookmall.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Shipping implements Serializable {
    // 필드 선언
    private static final long serialVersionUID = 8121814661110003493L;

    private String name; // 배송 고객 이름
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date date; // 배송일
    private Address addresss; // 배송 주소 객체

    // 생성자
    public Shipping(Address addresss) {
        this.addresss = addresss;
    }

    // getter setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Address getAddresss() {
        return addresss;
    }

    public void setAddresss(Address addresss) {
        this.addresss = addresss;
    }




}
