// package com.example.bookmall.validator;
//
// import com.example.bookmall.domain.Book;
// import org.springframework.stereotype.Component;
// import org.springframework.validation.Errors;
// import org.springframework.validation.Validator;
//
// @Component
// public class UnitsInStockValidator implements Validator {
//
//     // 1.Validator 인터페이스의 support() 메서드는 주어진 Book 클래스에 대한 유효성 검사 여부를 결정
//     public boolean supports(Class<?>clazz){
//         // Book 클래스의 유효성 검사 여부를 위한 메서드
//         return Book.class.isAssignableFrom(clazz);
//     }
//
//     // 2.Validator 인터페이스의 validate() 메서드는 주어진 Book 클래스에 대해 유효성 검사함
//     // 도서 가격이 1만원 이상이고, 재고가 99권을 초과하면 유효성 검사를 할 때 오류가 발생
//     // 그러면 Errors 객체의 rejectValue() 메서드가 메시지 리소스 파일에 설정된 오류 코드 UnitsInStockValidator.message의 메시지 출력
//     public void validate(Object target, Errors errors){
//         // Book 클래스의 우효성 검사 메서드
//         Book book = (Book)target;
//         if(book.getunit_price()>=10000&book.getUnitsInStock()>99){
//             // 오류 객체의 속성과 메시지 저장
//             errors.rejectValue("unitsInStock","UnitsInStockValidator.message");
//         }
//     }
// }
