package com.andrey_baburin.services;

import com.andrey_baburin.entity.Task;
import com.andrey_baburin.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    void createUser(User user);

    void updateUser(int id,User user);

    void deleteById(int id);
}
