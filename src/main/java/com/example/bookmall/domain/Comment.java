 package com.example.bookmall.domain;

 import jakarta.persistence.*;
 import lombok.AllArgsConstructor;
 import lombok.Builder;
 import lombok.Data;

 import java.time.LocalDateTime;
 import java.time.temporal.ChronoUnit;

 @Entity
 @Data
 @AllArgsConstructor
 @Builder
 public class Comment {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @ManyToOne
     @JoinColumn(name = "book_id")
     private Book book;

     @ManyToOne
     @JoinColumn(name = "user_id")
     private User user;

     private String content;

     private LocalDateTime createdAt;
     public Comment() {
         this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
     }
 }
