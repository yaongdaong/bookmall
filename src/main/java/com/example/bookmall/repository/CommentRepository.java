 package com.example.bookmall.repository;

 import com.example.bookmall.domain.Comment;
 import org.springframework.data.jpa.repository.JpaRepository;

 public interface CommentRepository extends JpaRepository<Comment, Long> {
 }
