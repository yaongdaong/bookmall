package com.example.bookmall.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    private String isbn; // 도서 ID
    private String book_name; // 도서명
    private int unit_price; // 가격
    private String author; // 저자
    private String description; // 설명
    private String publisher; // 출판사
    private String category; // 분류
    private Long units_in_stock;  // 재고 수
    private String release_date; // 출판일(월/년)
    private String b_condition; // 신규 도서 또는 중고 도서 또는 전자책
    private String file_name;
    private String file_path;
}
