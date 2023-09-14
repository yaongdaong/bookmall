package com.example.bookmall.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class ExampleInterceptor implements HandlerInterceptor {

    public Logger logger = LoggerFactory.getLogger(ExampleInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 요청 처리 전에 실행할 작업을 여기에 구현합니다.
        logger.info("preHandle() 호출...");

        if (handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            logger.info("핸들러 메소드명 : "+method.getMethod().getName());
        }
        return true; // true를 반환하면 요청 처리가 계속됩니다.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 요청 처리 후에 실행할 작업을 여기에 구현합니다.
        logger.info("postHandle() 호출...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // 요청 처리가 완료된 후에 실행할 작업을 여기에 구현합니다.
        logger.info("afterCompletion() 호출...");
    }
}
