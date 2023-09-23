package com.example.bookmall.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = MemberIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MemberId {
    String message() default "아이디는 admin입니다.";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
