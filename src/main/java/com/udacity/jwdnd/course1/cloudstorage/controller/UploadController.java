package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/upload")
public interface UploadController {
    @PostMapping
    String upload(@RequestParam("fileUpload") MultipartFile multipartFile, RedirectAttributes redirectAttributes, Authentication authentication);

    @DeleteMapping("/{id}")
    String remove(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Authentication authentication);
}
