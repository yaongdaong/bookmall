package com.example.bookmall.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.bookmall.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/exam10")
public class Example10Controller {
    @GetMapping
    // @ResponseBody :자바 객체를 HTTP 응답 body 내용으로 매핑하는 역할,
    // @RequestBody처럼 XML이나 JSON 형식을 갖춘 문자열 형태로 응답할 때 이용
    public @ResponseBody Person submit(){
        Person person = new Person();
        person.setName("HongGilSon");
        person.setAge("20");
        person.setEmail("Hong@naver.com");
        System.out.println(person);
        return person;
    }
}
