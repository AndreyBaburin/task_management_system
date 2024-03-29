package com.andrey_baburin.services;

import com.andrey_baburin.data.TaskDAO;
import com.andrey_baburin.data.UserDAO;
import com.andrey_baburin.entity.Task;
import com.andrey_baburin.entity.User;
import com.andrey_baburin.security.UserInformation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserProcessor implements UserService, UserDetailsService {
    private final UserDAO userDAO;

    public UserProcessor(@Qualifier("Hibernate") UserDAO userDAO) {
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
            throw new RuntimeException("No such USER in DB ");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userDAO.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User was not found in the database by email");
        }
        return user;
    }

    @Override
    public boolean exist(User user) {
        User newUser = userDAO.getUserByEmail(user.getEmail());
        return newUser != null;
    }

    @Override
    public void createUser(User user) {
        userDAO.addUser(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByEmail(username);
        if (user == null) {

            throw new UsernameNotFoundException("User was not found in the database by email");
        }
        return new UserInformation(user);
    }
}
