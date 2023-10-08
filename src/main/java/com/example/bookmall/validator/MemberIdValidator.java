package com.example.bookmall.validator;

import com.example.bookmall.domain.Member;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MemberIdValidator implements ConstraintValidator<MemberId, String> {
    // 1.ConstraintValidator 인터페이스는 매개변수 두 개를 정의한다.
    // 첫 번째 매개변수는 유효성 검사를 위해 MemberIdValidator 클래스를 사용하는 사용자정의 애너테이선을 지정
    // 두 번째 매개변수는 사용자 정의 애너테이션이 붙는 도메인 클래스 Member의 멤버 변수 타입을 설정
    private Member member;
    // 2.initialize() 메서드는 사용자 정의 애너테이션 @MemberId가 속성 값이 없기 때문에 구현 부분 없이 메서드만 선언
    public void initialize(MemberId constraintAnnotation){

    }
    // 3.isValid() 메서드는 ConstraintValidator<MemberId, String>에서 두번째 매개변수 타입으로 정의한 도메인 클래스의
    // 멤버 변수 값을 읽어 유효성 검사를 수행. 유효성 검사에서 오류가 발생하면 false를 반환한다.
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value.equals("admin")){
            return false;
        }
        return true;
    }
}
