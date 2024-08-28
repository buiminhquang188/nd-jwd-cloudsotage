package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.FileController;
import com.udacity.jwdnd.course1.cloudstorage.dto.Response;
import com.udacity.jwdnd.course1.cloudstorage.dto.ResponseDownload;
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

@Controller
public class FileControllerImpl implements FileController {
    private final FileService fileService;

    public FileControllerImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public String upload(MultipartFile multipartFile, RedirectAttributes redirectAttributes, Authentication authentication) {
        String error = null;
        if (multipartFile.isEmpty()) {
            error = "Please choose file before hit upload button";
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/home";
        }

        User user = (User) authentication.getPrincipal();
        boolean isUploaded = this.fileService.upload(multipartFile, user.getId());

        if (!isUploaded) {
            error = "There was an error during upload file, please try again";
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/home";
        }

        String success = "Upload successfully";
        redirectAttributes.addFlashAttribute("success", success);
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
