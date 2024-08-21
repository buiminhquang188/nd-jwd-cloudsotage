package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.HomeController;
import org.springframework.stereotype.Controller;

@Controller
public class HomeControllerImpl implements HomeController {
    @Override
    public String getHomePage() {
        return "home";
    }
}
