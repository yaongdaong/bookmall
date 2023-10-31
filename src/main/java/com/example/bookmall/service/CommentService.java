 package com.example.bookmall.service;

 import com.example.bookmall.domain.Book;
 import com.example.bookmall.domain.Comment;
 import com.example.bookmall.repository.CommentRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 @Service
 public class CommentService {
     @Autowired
     CommentRepository commentRepository;

     public Comment addComment(Book book, String content) {
         return null;
     }
 }
