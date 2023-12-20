package com.andrey_baburin.data;

import com.andrey_baburin.entity.Task;
import com.andrey_baburin.entity.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Qualifier("JDBC")
public class UserJdbcDAO implements UserDAO{
    private final Logger log = LoggerFactory.getLogger(TaskJdbcDAO.class);
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) {

    }

    @Override
    public List<User> getAllUsers() {
        log.info("SELECT* FROM users");
        String sql = "SELECT* FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getUserById(int id) {
        log.info("SELECT * FROM users WHERE ID = ?");
        User user;
        try {
            user = jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE ID = ?",
                    new BeanPropertyRowMapper<>(User.class), id);
        } catch (EmptyResultDataAccessException e) {
//            throw new TaskNotFoundException("No such task in DB for given ID");
            throw new RuntimeException("No such task in DB for given ID");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("SELECT * FROM users WHERE email = ?");
        User user;
        try {
            user = jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE email = ?",
                    new BeanPropertyRowMapper<>(User.class), email);
        } catch (EmptyResultDataAccessException e) {
//            throw new TaskNotFoundException("No such task in DB for given ID");
//            throw new RuntimeException("No such task in DB for given email");
            return null;
        }
        return user;
    }

    @Override
    public int updateUser(User user) {

        return 0;
    }

    @Override
    public void deleteUserById(int id) {

    }
}
