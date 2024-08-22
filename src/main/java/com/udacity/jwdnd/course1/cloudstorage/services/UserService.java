package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

public interface UserService {
    boolean createUser(User user);

    boolean isUsernameAvailable(String username);
}
