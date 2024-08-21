package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.SignupController;
import org.springframework.stereotype.Controller;

@Controller
public class SignupControllerImpl implements SignupController {
    @Override
    public String getSignupPage() {
        return "signup";
    }
}
