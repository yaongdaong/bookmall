package com.example.bookmall.validator;

import com.example.bookmall.domain.Book;
import com.example.bookmall.exception.BookIdException;
import com.example.bookmall.service.BookService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class BookIdValidator implements ConstraintValidator<BookId, String> {
    @Autowired
    private BookService bookService;
    // 1.initialize()메서드는 사용자 정의 애너테이션 @BookId의 관련 정보를 읽어 초기화 작업을 수행
    public void initialize(BookId constraintAnnotation){

    }
    // 2.isValid() 메서드는 도메인 클래스 Book의 bookid 속성 값을 읽어 유효성 검사를 수행
    // 여기에서 bookService.getBookById(0 메서드로 입력된 도서 ID가 이미 있다면 BookIdException 예외 처리가 발생
    public boolean isValid(String value, ConstraintValidatorContext context){
        Book book;
        try{
            book = bookService.getBookById(value);
        }catch(BookIdException e){
            return true;
        }
        if(book!=null){
            return false;
        }
        return true;
    }
}
