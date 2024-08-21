package com.udacity.jwdnd.course1.cloudstorage.controller.impl;

import com.udacity.jwdnd.course1.cloudstorage.controller.ResultController;
import org.springframework.stereotype.Controller;

@Controller
public class ResultControllerImpl implements ResultController {
    @Override
    public String getResultPage() {
        return "result";
    }
}
