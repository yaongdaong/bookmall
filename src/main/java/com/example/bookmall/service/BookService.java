package com.example.bookmall.service;

import com.example.bookmall.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookService {
    List<Book> getAllBookList();
    List<Book> getBookListByCategory(String category);
    Set<Book> getBookListByFilter(Map<String, List<String>>filter);
    Book getBookById(String bookId);
}
