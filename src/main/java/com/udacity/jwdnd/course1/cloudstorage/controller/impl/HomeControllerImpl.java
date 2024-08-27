package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.HomeController;
import com.udacity.jwdnd.course1.cloudstorage.enums.TABS;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HomeControllerImpl implements HomeController {
    private final HomeService homeService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeControllerImpl(HomeService homeService, NoteService noteService, CredentialService credentialService) {
        this.homeService = homeService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @Override
    public String getHomePage(Model model) {
        List<File> files = this.homeService.getFiles();
        model.addAttribute("files", files);

        List<Note> notes = this.noteService.getNotes();
        model.addAttribute("notes", notes);

        List<Credential> credentials = this.credentialService.getCredentials();
        model.addAttribute("credentials", credentials);

        this.activeTab(model);
        return "home";
    }

    private void activeTab(Model model) {
        if (model.getAttribute("tab") == null) {
            model.addAttribute("tab", TABS.FILES);
        }
    }
}
