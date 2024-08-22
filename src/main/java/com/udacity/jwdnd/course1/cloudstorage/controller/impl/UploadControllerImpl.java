package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.UploadController;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UploadControllerImpl implements UploadController {
    private final FileService fileService;

    public UploadControllerImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public RedirectView upload(MultipartFile multipartFile, RedirectAttributes redirectAttributes, Authentication authentication) {
        String error = null;
        if (multipartFile.isEmpty()) {
            error = "Please choose file before hit upload button";
            redirectAttributes.addFlashAttribute("error", error);
            return new RedirectView("home");
        }

        User user = (User) authentication.getPrincipal();
        boolean isUploaded = this.fileService.upload(multipartFile, user.getId());

        if (!isUploaded) {
            error = "There was an error during upload file, please try again";
            redirectAttributes.addFlashAttribute("error", error);
            return new RedirectView("home");
        }

        String success = "Upload successfully";
        redirectAttributes.addFlashAttribute("success", success);
        return new RedirectView("home");
    }
}
