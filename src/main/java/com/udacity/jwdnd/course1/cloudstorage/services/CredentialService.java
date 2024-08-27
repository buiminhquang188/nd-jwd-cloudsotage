package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.dto.Response;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

import java.util.List;

public interface CredentialService {
    List<Credential> getCredentials();

    Credential getCredential(int credentialId, int userId);

    boolean insert(Credential credential, int userId);

    Response update(Credential credential, int userId);

    Response delete(int credentialId, int userId);
}
