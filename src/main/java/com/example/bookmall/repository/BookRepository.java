package com.example.bookmall.repository;

import com.example.bookmall.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Page<Book> findByTitleContainingOrAuthorContaining(String title, String author, Pageable pageable);


}
