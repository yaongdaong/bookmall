package com.example.bookmall.controller;

import com.example.bookmall.domain.Book;
import com.example.bookmall.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
// 컨트롤러는 메서드를 포함하고 있는 일반적인 자바 클래스가 아니라 웹 브라우저에서 들어온 요청을 처리하는 메서드를 포함하고 있는 특정 자바 클래스

// 컨트롤러로 사용할 자바 클래스에 @Controller 선언
@Controller
public class BookController {
    // @AutoWired는 의존 관계에 있는 클래스의 프로퍼티(멤버 변수)에 대해 Setter()메서드를 대신하여 선언하는 애너테이션
    @Autowired
//    1.유연성,확장성을 높이기 위해 서비스객체인 BookService로 저장소 객체에 접근
    private BookService bookService;
//    2.요청매핑 : 사용자의 웹 요청 URL과 클래스 또는 메서드가 매핑되도록 @RequestMapping 설정
    @RequestMapping(value = "/books",method = RequestMethod.GET)
//    3.요청 처리 메서드 requestBookList()는 웹 요청을 처리할 메서드
    public String requestBookList(Model model){
        List<Book> list = bookService.getAllBookList();
//    4.모델 데이터: 모델 속성 이름 booklist에 저장된 도서 목록을 저장
        // Model 인터페이스는 사용자 요청에 대한 처리 결과를 뷰에 보여 주는 데 필요한 데이터를 Model 객체의 addAttribute() 메서드에 담아 전달합니다.
        // model.addAttibute(속성 이름,속성 값)
        model.addAttribute("bookList",list);
//    5. 뷰이름 반환 : 처리된 결과를 반환하도록 메서드 안에 뷰 이름이나 뷰 이름을 포함한 모델을 설정
        return "books";
    }

//    모델앤뷰 사용
    @GetMapping("/all")
    public ModelAndView requestAllBooks(){
//        1번. ModelAndView 클래스의 modelAndView 인스턴스를 생성
        ModelAndView modelAndView = new ModelAndView();
        List<Book> list = bookService.getAllBookList();
//        2번.도서 목록을 가져와 저장된 list 값을 모델 속성 bookList에 저장
        modelAndView.addObject("bookList",list);
//        3번.뷰 이름을 books로 설정하여 books.html 파일을 출력
        modelAndView.setViewName("books");
//        4번.ModelAndView 클래스의 modelAndView 인스턴스를 반환
        return modelAndView;
    }

}
