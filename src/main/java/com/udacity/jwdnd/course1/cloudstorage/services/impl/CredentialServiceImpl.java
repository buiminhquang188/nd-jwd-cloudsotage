package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.dto.Response;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialServiceImpl implements CredentialService {
    private final CredentialMapper credentialMapper;
    private final HashService hashService;

    public CredentialServiceImpl(CredentialMapper credentialMapper, HashService hashService) {
        this.credentialMapper = credentialMapper;
        this.hashService = hashService;
    }

    @Override
    public List<Credential> getCredentials() {
        return this.credentialMapper.getCredentials();
    }

    @Override
    public Credential getCredential(int credentialId, int userId) {
        return this.credentialMapper.getCredential(credentialId, userId);
    }

    @Override
    public boolean insert(Credential credential, int userId) {
        credential.setUserId(userId);

        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        String encodeSalt = Base64.getEncoder()
                .encodeToString(salt);
        String hashedPassword = this.hashService.getHashedValue(credential.getPassword(), encodeSalt);
        credential.setPassword(hashedPassword);

        return this.credentialMapper.insert(credential) > 0;
    }

    @Override
    public Response update(Credential credential, int userId) {
        Credential currentCredential = this.getCredential(credential.getId(), userId);
        if (currentCredential == null) {
            return new Response("Credential not found, please try again", false);
        }

        credential.setUserId(userId);
        int resultIndex = this.credentialMapper.update(credential);
        if (resultIndex < 0) {
            return new Response("There was an error when updating note, please try again", false);
        }

        return new Response(
                "Update Successfully",
                true
        );
    }

    @Override
    public Response delete(int credentialId, int userId) {
        Credential credential = this.getCredential(credentialId, userId);

        if (credential == null) {
            return new Response("Credential not found, please try again", false);
        }

        credential.setUserId(userId);
        int resultIndex = this.credentialMapper.delete(credentialId, userId);
        if (resultIndex < 0) {
            return new Response("There was an error when updating note, please try again", false);
        }

        return new Response(
                "Update Successfully",
                true
        );
    }
}
