package com.udacity.jwdnd.course1.cloudstorage.api;

import com.udacity.jwdnd.course1.cloudstorage.response.BaseResponse;
import com.udacity.jwdnd.course1.cloudstorage.response.CredentialResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface CredentialApiController {
    @GetMapping("/credential/{id}")
    ResponseEntity<BaseResponse<CredentialResponse>> getCredential(@PathVariable("id") Integer id, Authentication authentication);
}
