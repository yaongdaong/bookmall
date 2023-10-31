package com.example.bookmall.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AuditingInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String user;
    private String id;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().endsWith("books/add") && request.getMethod().equals("POST")) {
            user = request.getRemoteUser();
            id = request.getParameterValues("id")[0];
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (request.getRequestURI().endsWith("books/add")) {
            logger.warn(String.format("신규등록 도서 ID : %s, 접근자 : %s, 접근사각 : %s", id, user, getCurrentTime()));
        }
    }

    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());
    }
}
