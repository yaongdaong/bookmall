package com.example.bookmall.domain;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class Book {
    @Pattern(regexp="ISBN[1-9]+",message = "유효하지 않은 도서ID입니다(숫자로 조합하고 ISBN으로 시작하세요).")
    private String bookId; // 도서 ID
    @Size(min=4,max=50,message="유효하지 않은 도서명입니다(최소 4자에서 최대 50자까지 입력하세요).")
    private String name; // 도서명
    @Min(value=0,message="유효하지 않은 가격입니다(0이상의 수를 입력하세요).")
    @Digits(integer=8,fraction=2,message="유효하지 않은 가격입니다(소수점 2자리까지, 8자리까지 입력하세요).")
    @NotNull(message="유효하지 않은 가격입니다(가격을 입력하세요).")
    private int unitPrice; // 가격
    private String author; // 저자
    private String description; // 설명
    private String publisher; // 출판사
    private String category; // 분류
    private long unitsInStock;  // 재고 수
    private String releaseDate; // 출판일(월/년)
    private String condition; // 신규 도서 또는 중고 도서 또는 전자책



    private MultipartFile bookImage; //도서 이미지
    public Book() {
        super();
    }

    public Book(String bookId, String name, int unitPrice) {
        super();
        this.bookId = bookId;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    public MultipartFile getBookImage() {
        return bookImage;
    }

    public void setBookImage(MultipartFile bookImage) {
        this.bookImage = bookImage;
    }
}
