package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.dto.Response;
import com.udacity.jwdnd.course1.cloudstorage.dto.ResponseDownload;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper;

    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public boolean upload(MultipartFile multipartFile, Integer userId) throws IOException {
        File file = new File(
                null,
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                String.valueOf(multipartFile.getSize()),
                userId,
                multipartFile.getInputStream()
                        .readAllBytes());

        return this.fileMapper.insert(file) > 0;
    }

    @Override
    public File getFile(Integer fileId, Integer userId) {
        return this.fileMapper.getFile(fileId, userId);
    }

    @Override
    public File getFilename(String fileName, int userId) {
        return this.fileMapper.getFileByFilename(fileName, userId);
    }

    @Override
    public Response delete(Integer fileId, Integer userId) {
        File file = this.getFile(fileId, userId);
        if (file == null) {
            return new Response(
                    "File not found, please try again with other file",
                    false
            );
        }

        int resultIndex = this.fileMapper.delete(fileId);
        if (resultIndex < 0) {
            return new Response(
                    "There was an error when deleting file, please try again",
                    false
            );
        }

        return new Response(
                "Delete Successfully",
                true
        );
    }

    @Override
    public ResponseDownload<File> download(Integer fileId, Integer userId) {
        File file = this.getFile(fileId, userId);
        if (file == null) {
            return new ResponseDownload<>(
                    "File not found, please try again with other file",
                    false,
                    null
            );
        }

        return new ResponseDownload<>(
                "Download Successfully",
                true,
                file
        );
    }
}
