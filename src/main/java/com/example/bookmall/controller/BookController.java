package com.example.bookmall.controller;

import com.example.bookmall.domain.Book;
import com.example.bookmall.domain.Comment;
import com.example.bookmall.domain.User;
import com.example.bookmall.service.BookService;
import com.example.bookmall.service.CommentService;
import com.example.bookmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String addBook(Model model) {
        Book book = new Book(); // 또는 적절한 방식으로 Book 객체를 가져오세요
        model.addAttribute("book", book);
        System.out.println("book");
        model.addAttribute("heading", "도서 등록");
        model.addAttribute("subheading", "Add Book");
        return "addBook";
    }

    @PostMapping("/add")
    public String createBook(Book book, MultipartFile file) throws Exception {
        bookService.createBook(book, file);
        return "redirect:/books";
    }


    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("authenticatedUsername", username);
        //String username = userDetails != null ? userDetails.getUsername() : null;
        //model.addAttribute("username",username);
        System.out.println("username:"+username);
        //Optional<User> user = userService.findByUsername(username);
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        List<Comment> comments = commentService.getCommentsByBookId(id);
        model.addAttribute("comments", comments);
        String[] imageExtensions = {"jpeg", "png", "jpg"};
        model.addAttribute("imageExtensions", imageExtensions);
        model.addAttribute("heading", "도서 상세 정보");
        model.addAttribute("subheading", "Book Details");
        return "book";
    }

    @GetMapping
    public String getAllBooks(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model
    ) {
        int pageSize = 12; // 페이지 당 아이템 수
        Pageable pageable = PageRequest.of(page, pageSize); // 페이지네이션 설정

        Page<Book> books;
        if (keyword != null && !keyword.isEmpty()) {
            // 검색어가 제공된 경우, 검색 수행
            books = bookService.searchBooks(keyword, pageable);
        } else {
            // 검색어가 없는 경우, 모든 도서 조회
            books = bookService.findBooksByPage(pageable);
        }

        int startPage = Math.max(1, books.getPageable().getPageNumber() - 5);
        int endPage = Math.min(books.getTotalPages(), books.getPageable().getPageNumber() + 5);

        model.addAttribute("books", books);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("heading", "도서 목록");
        model.addAttribute("subheading", "Books List");

        // 검색어를 모델에 추가하여 템플릿에서 사용 가능
        model.addAttribute("keyword", keyword);

        return "books"; // books 템플릿으로 이동
    }

    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "updateBook";
    }

    @PostMapping("/update/{id}")
    public String bookUpdate(@PathVariable("id") Long id, @ModelAttribute Book updatedBook, @RequestParam("file") MultipartFile file) throws Exception {
        bookService.updateBook(id, updatedBook, file);
        return "redirect:/books";
    }


    @PostMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    //@PostMapping("/comment/{id}")
    //public String comment(@PathVariable Long id) {
    //    bookService.deleteBook(id);
    //    return "redirect:/books";
    //}
}
