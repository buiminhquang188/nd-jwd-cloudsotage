package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping
public interface CredentialController {
    @PostMapping("/credential")
    String addCredential(@ModelAttribute Credential credential, RedirectAttributes redirectAttributes, Authentication authentication);

    @PatchMapping("/credential/{id}")
    String updateCredential(@PathVariable("id") Integer id, @ModelAttribute Credential credential, RedirectAttributes redirectAttributes, Authentication authentication);

    @DeleteMapping("/credential/{id}")
    String deleteCredential(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Authentication authentication);
}
