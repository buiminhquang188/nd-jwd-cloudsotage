package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.FileController;
import com.udacity.jwdnd.course1.cloudstorage.dto.Response;
import com.udacity.jwdnd.course1.cloudstorage.dto.ResponseDownload;
import com.udacity.jwdnd.course1.cloudstorage.enums.FILE;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class FileControllerImpl implements FileController {
    private final FileService fileService;

    public FileControllerImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public String upload(MultipartFile multipartFile, RedirectAttributes redirectAttributes, Authentication authentication) {
        if (multipartFile.isEmpty()) {
            redirectAttributes.addFlashAttribute(FILE.MISSING_ERROR.getVariable(), FILE.MISSING_ERROR.getMessage());
            return "redirect:/home";
        }

        if (multipartFile.getSize() >= 5e+6) {
            redirectAttributes.addFlashAttribute(FILE.MAXIMUM_SIZE_ERROR.getVariable(), FILE.MAXIMUM_SIZE_ERROR.getMessage());
            return "redirect:/home";
        }

        User user = (User) authentication.getPrincipal();
        try {
            File file = this.fileService.getFilename(multipartFile.getOriginalFilename(), user.getId());
            if (file != null) {
                redirectAttributes.addFlashAttribute(FILE.DUPLICATE_NAME_ERROR.getVariable(), FILE.DUPLICATE_NAME_ERROR.getMessage());
                return "redirect:/home";
            }

            this.fileService.upload(multipartFile, user.getId());
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute(FILE.IO_ERROR.getVariable(), FILE.IO_ERROR.getMessage());
            return "redirect:/home";
        }

        redirectAttributes.addFlashAttribute(FILE.UPLOAD_SUCCESS.getVariable(), FILE.UPLOAD_SUCCESS.getMessage());
        return "redirect:/home";
    }

    @Override
    public String remove(Integer id, RedirectAttributes redirectAttributes, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Response response = this.fileService.delete(id, user.getId());

        if (!response.getIsSuccess()) {
            redirectAttributes.addFlashAttribute("error", response.getMessage());
            return "redirect:/home";
        }

        redirectAttributes.addFlashAttribute("success", response.getMessage());
        return "redirect:/home";
    }

    @Override
    public ResponseEntity<?> download(Integer id, RedirectAttributes redirectAttributes, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        ResponseDownload<File> fileResponseDownload = this.fileService.download(id, user.getId());

        if (!fileResponseDownload.getIsSuccess()) {
            redirectAttributes.addFlashAttribute("error", fileResponseDownload.getMessage());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/home");
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        }

        File file = fileResponseDownload.getData();
        ByteArrayResource byteArrayResource = new ByteArrayResource(file.getFileData());

        redirectAttributes.addFlashAttribute("success", fileResponseDownload.getMessage());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(byteArrayResource);
    }
}
