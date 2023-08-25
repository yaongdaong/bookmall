package com.example.bookmall.service;

import com.example.bookmall.domain.Book;
import com.example.bookmall.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBookList(){
        return bookRepository.getAllBookList();
    }
// getBookListByCategory()메서드는 웹 요청 URL에 포함된 경로 변수의 값을 저장소 객체에 전달한 후 실행하여 결과를 반환
// 1. 도서 분야와 일치하는 도서 목록을 저장소 객체에서 가져오는 getBookListByCategory() 메서드
    public List<Book> getBookListByCategory(String category){
        // 2. 저장소 객체에서 매개변수 category와 일치하는 도서 목록을 가져와 booksByCategory에 저장
        List<Book> booksByCategory = bookRepository.getBookListByCategory(category);
        // 3. 도서 목록이 저장된 booksByCategory를 반환
        return booksByCategory;
    }
    public Set<Book> getBookListByFilter(Map<String, List<String>>filter){
        Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter);
        return booksByFilter;
    }

    public Book getBookById(String bookId){
        Book bookById = bookRepository.getBookById(bookId);
        return bookById;
    }

}
