package com.andrey_baburin.services;

import com.andrey_baburin.data.TaskDAO;
import com.andrey_baburin.data.UserDAO;
import com.andrey_baburin.entity.Task;
import com.andrey_baburin.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class UserProcessor implements UserService{
    private final UserDAO userDAO;

    public UserProcessor(@Qualifier("JDBC") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        User user = userDAO.getUserById(id);
        if (user == null) {
//            throw new TaskNotFoundException();
            throw new RuntimeException("No such task in DB ");
        }
        return user;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(int id, User user) {

    }

    @Override
    public void deleteById(int id) {

    }
}
