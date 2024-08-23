package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

@RequestMapping
public interface FileController {
    @PostMapping("/upload")
    String upload(@RequestParam("fileUpload") MultipartFile multipartFile, RedirectAttributes redirectAttributes, Authentication authentication);

    @DeleteMapping("/upload/{id}")
    String remove(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Authentication authentication);

    @GetMapping("/download/{id}")
    String download(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Authentication authentication, HttpServletResponse response);
}
