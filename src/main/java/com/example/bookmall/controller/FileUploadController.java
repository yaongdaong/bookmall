package com.example.bookmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
public class FileUploadController {

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("bookImage") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 파일을 저장하거나 다른 작업을 수행
                byte[] bytes = file.getBytes();
                // 여기서 파일을 저장하거나 다른 작업을 수행합니다.
                // 예를 들어, 파일 시스템에 저장하거나 AWS S3에 업로드할 수 있습니다.

                return "redirect:/success"; // 성공 페이지로 이동
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 처리 중 오류 발생
                return "redirect:/error"; // 오류 페이지로 이동
            }
        } else {
            // 업로드된 파일이 비어있는 경우
            return "redirect:/error"; // 오류 페이지로 이동
        }
    }
}
