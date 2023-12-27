package com.andrey_baburin.services;

import com.andrey_baburin.entity.Task;
import com.andrey_baburin.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);
    User getUserByEmail(String email);

    void createUser(User user);

    boolean exist(User user);

}
