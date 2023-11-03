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
        // 현재 프로젝트 디렉토리 경로 얻기
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        // 이미지 이름 중복 방지를 위해 고유한 파일 이름 생성
        UUID uuid = UUID.randomUUID();
        // 업로드된 원래 파일이름과 UUID로 생성한 파일 이름을 조합해서 새 이름 만들기
        String fileName = uuid + "_" + file.getOriginalFilename();
        // projectPath와 fileName을 결합해서 이미지 파일을 저장할 경로를 나타내는 saveFile 객체 생성
        File saveFile = new File(projectPath, fileName);
        // 업로드된 이미지 파일을 실제 경로에 저장
        file.transferTo(saveFile);
        // 이미지 이름과 경로 설정
        book.setImage_name(fileName);
        book.setImage_path("/files/" + fileName);
        // book 객체를 데이터 베이스에 저장
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

    public Page<Book> searchBooks(String keyword, Pageable pageable) {
        return bookRepository.findByTitleContainingOrAuthorContaining(keyword, keyword, pageable);
    }

}
