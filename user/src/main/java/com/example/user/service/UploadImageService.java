package com.example.user.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadImageService {
//上传图片到本地
    public Map<String, String> upload(MultipartFile file);
}
