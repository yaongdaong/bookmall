package com.example.bookmall.service;

import com.example.bookmall.domain.Book;
import com.example.bookmall.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl  implements BookService{
    @Autowired
    private BookRepository bookRepository;

    // 1번
    public List<Book> getAllBookList(){
        return bookRepository.getAllBookList();
    }

}
