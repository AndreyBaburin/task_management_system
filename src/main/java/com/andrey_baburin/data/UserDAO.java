package com.andrey_baburin.data;

import com.andrey_baburin.entity.Task;
import com.andrey_baburin.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);
    User getUserByEmail(String email);

}
