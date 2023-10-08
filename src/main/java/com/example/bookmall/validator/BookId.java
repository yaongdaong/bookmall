package com.example.bookmall.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// 1.사용자 정의 애너테이션 @BookId는 Method, Field, Annotation_type을 정의하며, 이는 런타임할 때 적용된다.
// 2.도메인 클래스에 @BookId가 부여된 멤버 변수는 BookIdValidator 클래스로 유효사 검사를 수행.
@Constraint(validatedBy=BookIdValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BookId {
    // 2.@BookId는 필수 속성(message, groups, payload)을 포함
    // @BookId에 대한 유효성 검사를 할 때 오류가 발생하며 메시지 리소스 파일 messages.properties에 설정된 BookId.NewBook.bookId의 메시지를 출력
    String message() default "{BookId.NewBook.bookId}";
    Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};
}
