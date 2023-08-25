package com.example.bookmall.controller;

import com.example.bookmall.domain.Book;
import com.example.bookmall.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;
// 컨트롤러는 메서드를 포함하고 있는 일반적인 자바 클래스가 아니라 웹 브라우저에서 들어온 요청을 처리하는 메서드를 포함하고 있는 특정 자바 클래스

// 컨트롤러로 사용할 자바 클래스에 @Controller 선언
@Controller
@RequestMapping("/books")
public class BookController {
    // @AutoWired는 의존 관계에 있는 클래스의 프로퍼티(멤버 변수)에 대해 Setter()메서드를 대신하여 선언하는 애너테이션
    @Autowired
//    1.유연성,확장성을 높이기 위해 서비스객체인 BookService로 저장소 객체에 접근
    private BookService bookService;
//    2.요청매핑 : 사용자의 웹 요청 URL과 클래스 또는 메서드가 매핑되도록 @RequestMapping 설정
    @GetMapping
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

    // 1.@GetMapping에 설정된 요청 매핑 경로는 URL 템플릿 패턴으로 /{category}를 사용했고, 여기에서 category는 경로변수
    // @RequestMapping(value="/category", method=RequestMethod.GET) 또는 @ReqeustMapping("/category)와 같다.
    @GetMapping("/{category}")
    // 2.@PathVariable("category")를 선언하여 경로변수 category에 대해 매개변수 이름을 bookCategory로 재정의
    public String requestBooksByCategory(@PathVariable("category")String bookCategory, Model model){
        // 3.bookService.getBookListByCategory()메서드를 호출하여 매개변수 bookCategory와 일치하는 도서 목록을
        // 서비스 객체에서 가져와 booksByCategory에 저장
        List<Book> booksByCategory = bookService.getBookListByCategory(bookCategory);
        // 4. booksByCategory 값을 모델 속성 bookLsit에 저장
        model.addAttribute("bookList",booksByCategory);
        // 5. 뷰 이름인 books로 반환하므로 books.html이 파일이름
        return "books";
    }

    @GetMapping("/filter/{bookFilter}")
    public String requestBooksByFilter(
            @MatrixVariable(pathVar = "bookFilter") Map<String, List<String>> bookFilter, Model model){
        Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
        model.addAttribute("bookList",booksByFilter);
        return "books";
    }

    @GetMapping("/book")
    public String requestBookById(@RequestParam("id")String bookId, Model model){
        Book bookById = bookService.getBookById(bookId);
        model.addAttribute("book",bookById);
        return "book";
    }

    @GetMapping("/add")
    public String requestAddBookForm(Book book){
        return "addBook";
    }

}
