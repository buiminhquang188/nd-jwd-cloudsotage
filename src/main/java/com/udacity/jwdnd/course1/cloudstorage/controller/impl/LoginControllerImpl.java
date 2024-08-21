package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.LoginController;
import org.springframework.stereotype.Controller;

@Controller
public class LoginControllerImpl implements LoginController {
    @Override
    public String getLoginPage() {
        return "login";
    }
}
