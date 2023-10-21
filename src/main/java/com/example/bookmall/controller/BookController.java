package com.example.bookmall.controller;

import com.example.bookmall.domain.Book;
import com.example.bookmall.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
// 컨트롤러는 메서드를 포함하고 있는 일반적인 자바 클래스가 아니라 웹 브라우저에서 들어온 요청을 처리하는 메서드를 포함하고 있는 특정 자바 클래스

// 컨트롤러로 사용할 자바 클래스에 @Controller 선언
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        Book book = new Book(); // 또는 적절한 방식으로 Book 객체를 가져오세요
        model.addAttribute("book", book);
        System.out.println("book");
        return "addBook";
    }

    @PostMapping("/add")
    public String createBook(Book book, MultipartFile file)throws Exception {
        bookService.createBook(book, file);
        return "redirect:/books";
    }


    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);

        String[] imageExtensions = {"jpeg", "png", "jpg"};
        model.addAttribute("imageExtensions", imageExtensions);

        return "book";
    }

    @GetMapping
    public String getAllBooks(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        int pageSize = 10; // 페이지 당 아이템 수
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending()); // 필요에 따라 정렬 설정
        Page<Book> books = bookService.findBooksByPage(pageable);

        model.addAttribute("books", books);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", books.getTotalPages());
        model.addAttribute("heading", "도서 목록");
        model.addAttribute("subheading", "Books List");

        return "books";
    }

    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "updateBook";
    }

    @PostMapping("/update/{id}")
    public String bookUpdate(@PathVariable("id") Long id, Book book, MultipartFile file) throws Exception {
        Book bookTemp = bookService.getBookById(id);
        bookTemp.setIsbn(book.getIsbn());
        bookTemp.setBook_name(book.getBook_name());
        bookTemp.setUnit_price(book.getUnit_price());
        bookTemp.setAuthor(book.getAuthor());
        bookTemp.setDescription(book.getDescription());
        bookTemp.setPublisher(book.getPublisher());
        bookTemp.setCategory(book.getCategory());
        bookTemp.setUnits_in_stock(book.getUnits_in_stock());
        bookTemp.setRelease_date(book.getRelease_date());
        bookTemp.setB_condition(book.getB_condition());
        bookTemp.setFile_name(book.getFile_name());
        bookTemp.setFile_path(book.getFile_path());
        bookService.createBook(bookTemp, file);
        return "redirect:/books";
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
