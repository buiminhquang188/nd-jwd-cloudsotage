package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.SignupController;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class SignupControllerImpl implements SignupController {
    private final UserService userService;

    public SignupControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getSignupPage() {
        return "signup";
    }

    @Override
    public String signupUser(User user, Model model) {
        String signupError = null;

        if (this.userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username is already exists.";
        }

        if (signupError == null) {
            boolean isAdded = this.userService.createUser(user);

            if (!isAdded) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError != null) {
            model.addAttribute("signupError", signupError);
            return "signup";
        }

        model.addAttribute("signupSuccess", true);
        return "signup";
    }
}
