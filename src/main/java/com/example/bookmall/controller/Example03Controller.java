package com.example.bookmall.controller;

import com.example.bookmall.domain.Member;
import com.example.bookmall.domain.Person;
import com.example.bookmall.exception.Example03Exception;
import com.example.bookmall.validator.PersonValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Controller
public class Example03Controller {

    @GetMapping("/exam03")
    public String showForm() {
        return "webpage07_03";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "@ModelAttribute 유형";
    }

    @ModelAttribute
    public void setsubTitle(Model model) {
        model.addAttribute("subtitle", "메소드에 @ModelAttribute 애노테이션 적용하기");
    }

    @GetMapping("/exam03_1")
    public String requestMethod(Model model) {
        return "webpage08_03";
    }

    @GetMapping("/admin/tag_1")
    public String requestMethod2(Model model) {
        return "webpage08_3";
    }

    @GetMapping("/form_2")
    public String requestForm(Member member) {
        return "webpage09_02";
    }

    @PostMapping("/form_2")
    public String submitForm(@ModelAttribute("member") Member member, HttpServletRequest request, HttpSession session) {
        String name = member.getName();
        String filename = member.getImageFile().getOriginalFilename();
        try {
            member.getImageFile().transferTo(new File("c:\\upload\\" + name + "_" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "webpage09_submit";
    }

    @GetMapping("/exam03_2")
    public void handleRequest() {
        throw new Example03Exception();
    }

    @ExceptionHandler(Example03Exception.class)
    public ModelAndView handleException(Example03Exception ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errorMessage", ex.getErrMsg());
        model.addObject("exception", ex);
        model.setViewName("webpage10_03");
        return model;
    }

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam("lang") String lang, Model model) {
        // 요청에서 언어 변경
        Locale newLocale = new Locale(lang);
        LocaleContextHolder.setLocale(newLocale);
        // 변경된 언어에 맞는 메시지 가져오기
        String message = messageSource.getMessage("Person.form.Enter.message", null, LocaleContextHolder.getLocale());
        return "webpage12_02";
    }

    @Autowired
    private PersonValidator personValidator;

    @GetMapping("/exam03_3")
    public String showForm2(Model model){
        model.addAttribute("person",new Person());
        return "webpage13_03";
    }

    @PostMapping("/exam03_3")
    public String submit(@Valid @ModelAttribute Person person, BindingResult result){
        if (result.hasErrors()){
            return "webpage13_03";
        }
        return "webpage13_03";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(personValidator);
    }
}
