package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.dto.Response;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean upload(MultipartFile multipartFile, Integer userId);

    File getFile(Integer fileId, Integer userId);

    Response delete(Integer fileId, Integer userId);
}
