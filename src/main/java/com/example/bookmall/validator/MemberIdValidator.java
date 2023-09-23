package com.example.bookmall.validator;

import com.example.bookmall.domain.Member;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MemberIdValidator implements ConstraintValidator<MemberId, String> {
    private Member member;
    public void initialize(MemberId constraintAnnotation){

    }
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value.equals("admin")){
            return false;
        }
        return true;
    }
}
