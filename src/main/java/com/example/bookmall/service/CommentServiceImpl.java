// package com.example.bookmall.service;
//
// import com.example.bookmall.domain.Book;
// import com.example.bookmall.domain.Comment;
// import com.example.bookmall.repository.CommentRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// import java.util.Date;
//
// @Service
// public class CommentServiceImpl implements CommentService {
//     private final CommentRepository commentRepository;
//
//     @Autowired
//     public CommentServiceImpl(CommentRepository commentRepository) {
//         this.commentRepository = commentRepository;
//     }
//
//     @Override
//     public Comment addComment(Book book, String content) {
//         Comment comment = new Comment();
//         comment.setBook(book);
//         comment.setContent(content);
//         comment.setCreatedDate(new Date());
//         return commentRepository.save(comment);
//     }
// }
