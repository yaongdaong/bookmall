package com.example.bookmall.validator;

import com.example.bookmall.domain.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        // Validate name
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Name is required");

        // Validate age
        String age = person.getAge();
        if (age == null || age.trim().isEmpty()) {
            errors.rejectValue("age", "age.empty", "Age is required");
        } else {
            try {
                int ageValue = Integer.parseInt(age);
                if (ageValue < 0 || ageValue > 120) {
                    errors.rejectValue("age", "age.invalid", "Invalid age");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue("age", "age.invalid", "Invalid age format");
            }
        }

        // Validate email
        String email = person.getEmail();
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.rejectValue("email", "email.invalid", "Invalid email address");
        }
    }
}

// package com.example.bookmall.validator;
//
// import com.example.bookmall.domain.Person;
// import org.springframework.stereotype.Component;
// import org.springframework.validation.Errors;
// import org.springframework.validation.Validator;
// @Component
// public class PersonValidator implements Validator{
//     public boolean supports(Class<?> clazz){
//         return Person.class.isAssignableFrom(clazz);
//     }
//     public void validate(Object target, Errors errors){
//         Person person = (Person) target;
//
//         String name = person.getName();
//         if (name == null || name.trim().isEmpty()){
//             errors.rejectValue("name","name.not.empty");
//         }
//
//         String age = person.getAge();
//         if (age == null || age.trim().isEmpty()){
//             errors.rejectValue("age","age.not.inrange");
//         }
//
//         String email = person.getEmail();
//         if (email == null || email.trim().isEmpty()){
//             errors.rejectValue("email","email.not.correct");
//         }
//     }
// }
