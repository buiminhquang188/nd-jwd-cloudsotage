package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.HomeController;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HomeControllerImpl implements HomeController {
    private final HomeService homeService;

    public HomeControllerImpl(HomeService homeService) {
        this.homeService = homeService;
    }

    @Override
    public String getHomePage(Model model) {
        List<File> files = this.homeService.getFiles();
        model.addAttribute("files", files);
        return "home";
    }
}
