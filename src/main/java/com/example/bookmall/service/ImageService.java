package com.example.bookmall.service;

import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {
    public String saveImage(MultipartFile file) throws IOException {
        // 현재 프로젝트 디렉토리 경로 얻기
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        // 이미지 이름 중복 방지를 위해 고유한 파일 이름 생성
        UUID uuid = UUID.randomUUID();
        // 업로드된 원래 파일이름과 UUID로 생성한 파일 이름을 조합해서 새 이름 만들기
        String fileName = uuid + "_" + file.getOriginalFilename();
        // projectPath와 fileName을 결합해서 이미지 파일을 저장할 경로를 나타내는 saveFile 객체 생성
        File saveFile = new File(projectPath, fileName);
        // 업로드된 이미지 파일을 실제 경로에 저장
        file.transferTo(saveFile);
        // 이미지 이름과 경로 설정
        return fileName;
    }

    public void deleteImage(String filePath) {
        try {
            // 전달받은 파일 경로로 File 객체 생성
            File fileToDelete = new File(filePath);

            // 파일이 존재하면 삭제
            if (fileToDelete.exists()) {
                if (fileToDelete.delete()) {
                    System.out.println("이미지 삭제 성공: " + filePath);
                } else {
                    System.out.println("이미지 삭제 실패: " + filePath);
                }
            } else {
                System.out.println("존재하지 않는 이미지 파일: " + filePath);
            }

        } catch (Exception e) {
            System.out.println("이미지 삭제 중 오류 발생: " + e.getMessage());
        }
    }
}
