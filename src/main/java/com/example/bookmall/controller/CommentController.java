 package com.example.bookmall.controller;

 import com.example.bookmall.domain.Book;
 import com.example.bookmall.domain.Comment;
 import com.example.bookmall.domain.CommentRequest;
 import com.example.bookmall.service.CommentService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;

 @RestController
 @RequestMapping("/api/comments")
 public class CommentController {
     private final CommentService commentService;

     @Autowired
     public CommentController(CommentService commentService) {
         this.commentService = commentService;
     }

     @PostMapping
     public Comment addComment(@RequestBody CommentRequest commentRequest) {
         Book book = new Book();
         book.setId(Math.toIntExact(commentRequest.getid()));
         return commentService.addComment(book, commentRequest.getContent());
     }
 }
