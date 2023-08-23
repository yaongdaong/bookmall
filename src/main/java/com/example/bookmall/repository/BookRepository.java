package com.example.bookmall.repository;

import com.example.bookmall.domain.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBookList();

    //    @PathVariable을 이용하여 웹 요청 URL에 전송된 도서 분야(category)의 값을 경로 변수 category로 전달 받음
    //    저장소 객체(메모리 저장소)에 저장된 도서 목록 중 경로 변수의 값과 일치하는 도서를 검색하여 도서 목록을 출력
    List<Book> getBookListByCategory(String category);
}
