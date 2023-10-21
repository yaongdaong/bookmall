package com.example.bookmall.service;

import com.example.bookmall.domain.Book;
import com.example.bookmall.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookrepository){
        this.bookRepository = bookrepository;
    }

    public Book createBook(Book book, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        book.setFile_name(fileName);
        book.setFile_path("/files/" + fileName);
        return bookRepository.save(book);
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }


    public Page<Book> findBooksByPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    public Book updateBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
