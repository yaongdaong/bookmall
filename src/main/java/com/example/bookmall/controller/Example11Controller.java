package com.example.bookmall.controller;

import com.example.bookmall.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RestController 애너테이션은 컨트롤러에 @ResponseBody가 추가된것,JSON 형태로 데이터를 반환.
// @Controller와는 다르게 @RestController는 반환 값에 자동으로 @ResponseBody가 붙어
// 자바 객체가 HTTP 응답 body 내용에 매핑되어 전달된다.
// @RestController를 사용하며 @ResponseBody를 사용하지 않아도 되지만,
// @Controller일 때는 반드시 @ResponseBody를 선언해야함
@RequestMapping("/exam11")
public class Example11Controller {
    @GetMapping
    public Person submit(){
        Person person = new Person();
        person.setName("HongGilSon");
        person.setAge("20");
        person.setEmail("Hong@naver.com");
        System.out.println(person);
        return person;
    }
}
