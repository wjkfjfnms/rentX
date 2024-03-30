package com.example.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageService {
//上传图片到本地
    public String upload(MultipartFile file);
}
