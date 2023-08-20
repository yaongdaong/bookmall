package com.example.bookmall.repository;

import com.example.bookmall.domain.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBookList();
}
