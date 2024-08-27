package com.udacity.jwdnd.course1.cloudstorage.api.impl;

import com.udacity.jwdnd.course1.cloudstorage.api.CredentialApiController;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.response.BaseResponse;
import com.udacity.jwdnd.course1.cloudstorage.response.CredentialResponse;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class CredentialApiControllerImpl implements CredentialApiController {
    private final CredentialService credentialService;

    public CredentialApiControllerImpl(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @Override
    public ResponseEntity<BaseResponse<CredentialResponse>> getCredential(Integer id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Credential credential = this.credentialService.getCredential(id, user.getId());
        CredentialResponse credentialResponse = new CredentialResponse(
                credential.getId(),
                credential.getUrl(),
                credential.getUsername(),
                credential.getPassword()
        );

        return ResponseEntity.ok(
                new BaseResponse<>(
                        HttpStatus.OK.getReasonPhrase(),
                        HttpStatus.OK.value(),
                        credentialResponse
                )
        );
    }
}
