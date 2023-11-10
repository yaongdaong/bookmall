package com.example.bookmall.service;

import com.example.bookmall.domain.Book;
import com.example.bookmall.repository.BookRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookrepository) {
        this.bookRepository = bookrepository;
    }

    @Autowired
    private ImageService imageService;

    public Book createBook(Book book, MultipartFile file) throws Exception {
        String fileName = imageService.saveImage(file);
        String filePath = "/files/" + fileName;
        book.setImage_name(fileName);
        book.setImage_path(filePath);
        // book 객체를 데이터 베이스에 저장
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }


    public Page<Book> findBooksByPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book updateBook(Long id, Book updatedBook,MultipartFile file) throws IOException {
        Optional<Book> bookTemp = bookRepository.findById(id);
        if (bookTemp.isPresent()) {
            Book book = bookTemp.get();

            if(file!= null&& !file.isEmpty()){
                imageService.deleteImage(book.getImage_name());
                String newFileName = imageService.saveImage(file);
                book.setImage_name(newFileName);
                book.setImage_path("/files/"+newFileName);
            }
            book.setIsbn(updatedBook.getIsbn());
            book.setTitle(updatedBook.getTitle());
            book.setUnit_price(updatedBook.getUnit_price());
            book.setAuthor(updatedBook.getAuthor());
            book.setDescription(updatedBook.getDescription());
            book.setPublisher(updatedBook.getPublisher());
            book.setCategory(updatedBook.getCategory());
            book.setUnits_in_stock(updatedBook.getUnits_in_stock());

            book.setRelease_date(updatedBook.getRelease_date());
            book.setB_condition(updatedBook.getB_condition());
            book.setImage_name(updatedBook.getImage_name());
            book.setImage_path(updatedBook.getImage_path());

            return bookRepository.save(book);
        }
        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Page<Book> searchBooks(String keyword, Pageable pageable) {
        return bookRepository.findByTitleContainingOrAuthorContaining(keyword, keyword, pageable);
    }

}
