package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean upload(MultipartFile multipartFile, Integer userId);
}
