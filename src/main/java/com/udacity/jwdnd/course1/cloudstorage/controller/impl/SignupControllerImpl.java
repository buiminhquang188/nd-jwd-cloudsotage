package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.SignupController;
import com.udacity.jwdnd.course1.cloudstorage.enums.SIGNUP;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String signupUser(User user, Model model, RedirectAttributes redirectAttributes) {
        String signupError = null;

        if (this.userService.isUsernameAvailable(user.getUsername())) {
            signupError = SIGNUP.USER_EXIST_ERROR.getObject()
                    .toString();
        }

        if (signupError == null) {
            boolean isAdded = this.userService.createUser(user);

            if (!isAdded) {
                signupError = SIGNUP.INTERNAL_ERROR.getObject()
                        .toString();
            }
        }

        if (signupError != null) {
            model.addAttribute("signupError", signupError);
            return "signup";
        }

        redirectAttributes.addFlashAttribute("signupSuccess", SIGNUP.SUCCESS.getObject());
        return "redirect:/login";
    }
}
