package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.dto.Response;
import com.udacity.jwdnd.course1.cloudstorage.dto.ResponseDownload;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    boolean upload(MultipartFile multipartFile, Integer userId) throws IOException;

    File getFile(Integer fileId, Integer userId);

    File getFilename(String fileName, int userId);

    Response delete(Integer fileId, Integer userId);

    ResponseDownload<File> download(Integer fileId, Integer userId);
}
