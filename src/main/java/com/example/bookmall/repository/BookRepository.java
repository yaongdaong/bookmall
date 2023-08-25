package com.example.bookmall.repository;

import com.example.bookmall.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookRepository {
    List<Book> getAllBookList();

    // @PathVariable을 이용하여 웹 요청 URL에 전송된 도서 분야(category)의 값을 경로 변수 category로 전달 받음
    // 저장소 객체(메모리 저장소)에 저장된 도서 목록 중 경로 변수의 값과 일치하는 도서를 검색하여 도서 목록을 출력
    List<Book> getBookListByCategory(String category);

    // @MatrixVariable을 이용하여 웹 요청 URL에 포함된 도서 분야 및 출판사를 전달받아 저장소 객체(메모리 저장소)에 저장된
    // 도서 목록 중에서 매트릭수 변수 값과 일치하는 도서를 검색하여 도서 목록을 출력하는 요청 처리 메서드를 구현
    Set<Book> getBookListByFilter(Map<String, List<String>> filter);
    // 도서 ID와 일치하는 도서를 검색ㄹ
    Book getBookById(String bookId);
}
