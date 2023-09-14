package com.example.bookmall;

import com.example.bookmall.interceptor.AuditingInterceptor;
import com.example.bookmall.interceptor.ExampleInterceptor;
import com.example.bookmall.interceptor.MonitoringInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BookMallApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(BookMallApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 사용자 정의 인터셉터를 등록합니다.
        registry.addInterceptor(new MonitoringInterceptor());
        registry.addInterceptor(new ExampleInterceptor());
        registry.addInterceptor(new AuditingInterceptor());
    }

}