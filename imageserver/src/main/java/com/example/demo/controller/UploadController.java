package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    private static final String UPLOAD_DIR = "/data/uploads"; // PVC로 마운트된 경로

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("image") MultipartFile file) {
        try {
            // 디렉토리 없으면 생성
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) dir.mkdirs();

            // 저장 경로 지정
            String filePath = UPLOAD_DIR + File.separator + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);

            return ResponseEntity.ok("파일 업로드 성공: " + dest.getName());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("파일 업로드 실패: " + e.getMessage());
        }
    }
    
    @GetMapping("/images")
    public ResponseEntity<List<String>> listImages() {
        File dir = new File("/data/uploads");
        String[] files = dir.list((d, name) -> name.matches(".*\\.(jpg|png|jpeg|gif)"));
        return ResponseEntity.ok(Arrays.asList(files != null ? files : new String[0]));
    }

}
