 package com.example.bookmall.service;

 import com.example.bookmall.domain.Book;
 import com.example.bookmall.domain.Comment;
 import com.example.bookmall.domain.User;
 import com.example.bookmall.repository.CommentRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.time.LocalDateTime;
 import java.util.List;
 import java.util.Optional;

 @Service
 public class CommentService {
     @Autowired
     CommentRepository commentRepository;

     public Comment addComment(Book book, String content, Optional<User> user) {
         Comment comment = new Comment();
         comment.setBook(book);
         comment.setContent(content);
         comment.setCreatedAt(LocalDateTime.now());
         user.ifPresent(comment::setUser);
         return commentRepository.save(comment);
     }

     public List<Comment> getCommentsByBookId(Long bookId) {
         return commentRepository.findByBookId(bookId);
     }

     public Comment updateComment(Long commentId, String newContent){
         Comment comment = commentRepository.findById(commentId)
         .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
         comment.setContent(newContent);
         return commentRepository.save(comment);
     }

     public void deleteComment(Long id){
         commentRepository.deleteById(id);
     }
 }
