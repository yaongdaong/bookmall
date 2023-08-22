package com.example.bookmall.controller;

import com.example.bookmall.domain.Book;
import com.example.bookmall.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BookController {
    @Autowired
//    1.유연성,확장성을 높이기 위해 서비스객체인 BookService로 저장소 객체에 접근
    private BookService bookService;
//    2.요청매핑
    @RequestMapping(value = "/books",method = RequestMethod.GET)
//    3.요청 처리 메서드 requestBookList()는 웹 요청을 처리할 메서드
    public String requestBookList(Model model){
        List<Book> list = bookService.getAllBookList();
//    4.모델 데이터: 모델 속성 이름 booklist에 저장된 도서 목록을 저장
        model.addAttribute("bookList",list);
//    5. 뷰이름 반환
        return "books";
    }

}
