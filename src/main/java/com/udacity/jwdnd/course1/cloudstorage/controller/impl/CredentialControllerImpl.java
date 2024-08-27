package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.CredentialController;
import com.udacity.jwdnd.course1.cloudstorage.enums.TABS;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CredentialControllerImpl implements CredentialController {
    private final CredentialService credentialService;

    public CredentialControllerImpl(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @Override
    public String addCredential(Credential credential, RedirectAttributes redirectAttributes, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        boolean isInserted = this.credentialService.insert(credential, user.getId());
        redirectAttributes.addFlashAttribute("tab", TABS.CREDENTIALS);

        if (!isInserted) {
            String error = "There was an error during create credential, please try again.";
            redirectAttributes.addFlashAttribute("errorCredential", error);
            redirectAttributes.addFlashAttribute("credential", credential);
            return "redirect:/home";
        }


        List<Credential> credentials = this.credentialService.getCredentials();
        redirectAttributes.addFlashAttribute("notes", credentials);
        redirectAttributes.addFlashAttribute("successCredential", "Add Successfully");
        return "redirect:/home";
    }

    @Override
    public String updateCredential(Integer id, Credential credential, RedirectAttributes redirectAttributes, Authentication authentication) {
        return "";
    }

    @Override
    public String deleteCredential(Integer id, RedirectAttributes redirectAttributes, Authentication authentication) {
        return "";
    }
}
