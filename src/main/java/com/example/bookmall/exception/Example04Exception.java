package com.example.bookmall.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages={"com.springmvc"})
public class Example04Exception {
    @ExceptionHandler(value={RuntimeException.class})
    private ModelAndView handleErrorMethod(Exception ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage","Example04Exception 메시지입니다.");
        modelAndView.setViewName("webpage10_03");
        return modelAndView;
    }
}
