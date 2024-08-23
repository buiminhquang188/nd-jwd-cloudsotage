package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.FileController;
import com.udacity.jwdnd.course1.cloudstorage.dto.Response;
import com.udacity.jwdnd.course1.cloudstorage.dto.ResponseDownload;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

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
    public String download(Integer id, RedirectAttributes redirectAttributes, Authentication authentication, HttpServletResponse response) {
        User user = (User) authentication.getPrincipal();
        ResponseDownload<File> fileResponseDownload = this.fileService.download(id, user.getId());

        if (!fileResponseDownload.getIsSuccess()) {
            redirectAttributes.addFlashAttribute("error", fileResponseDownload.getMessage());
            return "redirect:/home";
        }

        try {
            File file = fileResponseDownload.getData();
            OutputStream out = response.getOutputStream();

            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getFileName() + "\"");
            response.setContentType(file.getContentType());
            IOUtils.copy(new ByteArrayInputStream(file.getFileData()), out);

            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        redirectAttributes.addFlashAttribute("success", fileResponseDownload.getMessage());
        return "redirect:/home";
    }
}
