package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final HashService hashService;

    public UserServiceImpl(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public boolean createUser(User user) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        String encodeSalt = Base64.getEncoder()
                .encodeToString(salt);
        String hashedPassword = this.hashService.getHashedValue(user.getPassword(), encodeSalt);

        return this.userMapper.insert(new User(null, user.getUsername(), encodeSalt, hashedPassword, user.getFirstName(), user.getLastName())) >= 0;
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return this.userMapper.getUser(username) != null;
    }
}
