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

    public Book updateBook(Long id, Book updatedBook, MultipartFile file) throws IOException {
        Optional<Book> bookTemp = bookRepository.findById(id);
        if (bookTemp.isPresent()) {
            Book book = bookTemp.get();

            // Debugging: Check if the file is received and not empty
            System.out.println("Received file: " + file.getOriginalFilename());

            // Debugging: Log the existing image name before deletion
            System.out.println("Existing image name: " + book.getImage_name());

            // Delete existing image if the name is not null
            if (book.getImage_name() != null) {
                // Debugging: Check if the file is being deleted
                try {
                    imageService.deleteImage(book.getImage_name());
                    System.out.println("Image deletion successful: " + book.getImage_name());
                } catch (Exception e) {
                    System.out.println("Image deletion error: " + e.getMessage());
                }
            } else {
                System.out.println("No existing image to delete");
            }

            // Debugging: Check if the file is being saved
            String newFileName = imageService.saveImage(file);
            System.out.println("New image name: " + newFileName);

            // Update book properties
            book.setImage_name(newFileName);
            book.setImage_path("/files/" + newFileName);

            // Update other book properties
            book.setIsbn(updatedBook.getIsbn());
            book.setTitle(updatedBook.getTitle());
            // ... (update other properties)

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
