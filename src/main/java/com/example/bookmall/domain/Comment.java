 package com.example.bookmall.domain;

 import jakarta.persistence.*;
 import lombok.AllArgsConstructor;
 import lombok.Builder;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import java.util.Date;

 @Entity
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Builder
 public class Comment {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @ManyToOne
     @JoinColumn(name = "book_id")
     private Book book;

     private String content;

     @Temporal(TemporalType.TIMESTAMP)
     private Date createdDate;
 }
