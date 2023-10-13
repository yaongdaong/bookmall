package com.example.bookmall.domain;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {
    // 필드 선언
    private static final long serialVersionUID = 613846598817670033L;
    private String detailName; // 세부 주소
    private String addressName; // 주소
    private String country; // 국가명
    private String zipCode; // 우편번호

    // getter() setter()
    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    // equal() hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(detailName, address.detailName) && Objects.equals(addressName, address.addressName) && Objects.equals(country, address.country) && Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailName, addressName, country, zipCode);
    }



}
