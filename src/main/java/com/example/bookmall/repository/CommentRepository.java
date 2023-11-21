 package com.example.bookmall.repository;

 import com.example.bookmall.domain.Comment;
 import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.List;

 public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByBookId(Long bookId);
 }
