package com.example.bookmall.controller.example;

import com.example.bookmall.domain.Member;
import com.example.bookmall.domain.Product;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Controller
public class Example01Controller {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "요청 실패했습니다.")

    @GetMapping("/exam01/{bookId}")
    public String requestMethod(@PathVariable String bookId, Model model) {
        System.out.println("도서 ID : " + bookId);
        model.addAttribute("data", "도서 ID : " + bookId);
        return "webpage06";
    }

    @GetMapping("/member")
    public String showForm(Model model) {
        Member member = new Member();
        System.out.println("@GetMapping-------------");
        System.out.println("아이디: " + member.getId());
        System.out.println("비밀번호: " + member.getPassword());
        System.out.println("거주지: " + member.getCity());
        System.out.println("성별: " + member.getSex());
        System.out.println("취미: " + member.getHobby());
        model.addAttribute("member", member);
        return "webpage07_01";
    }

    @GetMapping("/exam01")
    public String requestMethod(Model model) {
        return "webpage08_01";
    }

    @GetMapping("/admin/main")
    public String requestMethod2(Model model) {
        model.addAttribute("data", "/webpage01/adminPage.html");
        return "webpage01/adminPage";
    }

    @GetMapping("/manager/main")
    public String requestMethod3(Model model) {
        model.addAttribute("data", "/webpage01/managerPage.html");
        return "webpage01/managerPage";
    }

    @GetMapping("/member/main")
    public String requestMethod4(Model model) {
        model.addAttribute("data", "/webpage01/memberPage.html");
        return "webpage01/memberPage";
    }

    @GetMapping("/home/main")
    public String requestMethod5(Model model) {
        model.addAttribute("data", "/webpage01/homePage.html");
        return "webpage01/homePage";
    }

    @GetMapping("/form")
    public String requestForm() {
        return "webpage09_01";
    }

    @PostMapping("/form")
    public String submitForm(@RequestParam("name") String name, @RequestParam("fileImage") MultipartFile file) {
        String filename = file.getOriginalFilename();
        File f = new File("c:\\upload\\" + name + "_" + filename);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "webpage09_submit";
    }

    @GetMapping("exam01_1")
    public String requestMethotd(Model model) {
        System.out.println("chapter10_01 예제입니다.");
        model.addAttribute("data", "@ResponseStatus 처리 예제입니다.");
        return "webpage10_01";
    }

public static Logger logger = LoggerFactory.getLogger(Example01Controller.class);
    @GetMapping("/exam01_2")
    public String request1Method(Model model){
        logger.trace("Trace 메시지!");
        logger.debug("Debug 메시지!");
        logger.info("Info 메시지!");
        logger.warn("Warn 메시지!");
        logger.error("Error 메시지!");
        return "webpage11_01";
    }

        @Autowired
        private MessageSource messageSource;

        @GetMapping("/welcome")
        public String welcome(Model model) {
            String welcomeMessage = messageSource.getMessage("welcome.message", null, Locale.getDefault());
            String errorMessage = messageSource.getMessage("error.message", null, Locale.getDefault());
            model.addAttribute("welcomeMessage", welcomeMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "webpage12_01";
        }

        @GetMapping("/exam01_3")
    public String showForm1(Model model){
            model.addAttribute("product",new Product());
            String notnullMessage = messageSource.getMessage("NotNull.Product.name", null, Locale.getDefault());
            model.addAttribute("notnullMessage", notnullMessage);
            String priceMessage = messageSource.getMessage("Min.Product.price", null, Locale.getDefault());
            model.addAttribute("priceMessage", priceMessage);
            return "webpage13_01";
        }

        @PostMapping("/exam01_3")
    public String submit(@Valid @ModelAttribute Product product, Errors errors){
            if (errors.hasErrors())
                return "webpage13_01";
            return "webpage13_01";
        }


}
