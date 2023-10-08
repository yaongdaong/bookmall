// package com.example.bookmall.validator;
//
// import com.example.bookmall.domain.Book;
// import jakarta.validation.ConstraintViolation;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.validation.Errors;
// import org.springframework.validation.Validator;
//
// import java.util.HashSet;
// import java.util.Set;
//
// public class BookValidator implements Validator {
//     @Autowired
//     // 1.bean validation(JSR-303 validator)의 인스턴스를 선언
//     private javax.validation.Validator beanValidator;
//
//     // 2.spring validation(Validator 인터페이스)의 인스턴스를 선언
//
//     private Set<Validator> springValidators;
//
//     // 3.BookValidator 클래스의 생성자
//     public BookValidator() {
//         springValidators = new HashSet<Validator>();
//     }
//     // 4.springValidators의 Setter() 메서드
//
//     public void setSpringValidator(Set<Validator> springValidators) {
//         this.springValidators = springValidators;
//     }
//
//     // 5.Book 클래스의 유효성 검사 메서드
//     public boolean supports(Class<?> clazz) {
//         return Book.class.isAssignableFrom(clazz);
//     }
//
//     // 6.Book 클래스의 유효성 검사 메서드
//     public void validate(Object target, Errors errors) {
//         // Bean Validation 설정
//         Set<ConstraintViolation<Object>> violations = beanValidator.validate(target);
//         // 7.bean validation 오류 저장
//         for (ConstraintViolation<Object> violation : violations) {
//             // 오류 발생 필드 저장
//             String propertyPath = violation.getPropertyPath().toString();
//             String message = violation.getMessage(); // 오류 발생 메시지 저장
//             // 오류가 발생된 필드와 메시지를 Errors 객체에 저장
//             errors.rejectValue(propertyPath, "", message);
//         }
//         // 8.spring validation 오류 저장
//         for (Validator validator : springValidators) {
//             validator.validate(target, errors); // 발생된 오류 정보를 전달
//         }
//     }
// }
