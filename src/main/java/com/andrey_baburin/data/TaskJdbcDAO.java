package com.andrey_baburin.data;

import com.andrey_baburin.entity.Task;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Qualifier("JDBC")
public class TaskJdbcDAO implements TaskDAO {

    private final Logger log = LoggerFactory.getLogger(TaskJdbcDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private final UserDAO userDAO;

    @Override
    public void addTask(Task task) {
        log.info("addTask INSERT INTO tasks");
        String sql = "INSERT INTO tasks (title, description, status, priority, user_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, task.getTitle(), task.getDescription(),  task.getStatus(),
                task.getPriority(), task.getUser().getId());
    }

    @Override
    public int updateTask(Task task) {
        log.info("UPDATE tasks SET");
        log.info(task.toString());
        String sql = "UPDATE tasks SET title = ?, description = ?, status = ?," +
                " priority = ?, user_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, task.getTitle(), task.getDescription(),task.getStatus(),
                task.getPriority(),task.getUser().getId(), task.getId());
    }

    @Override
    public List<Task> getAllTasks() {
        log.info("SELECT * FROM tasks");
        String sql = "SELECT * FROM tasks";
        return jdbcTemplate.query(sql, new RowMapper<Task>() {
            public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
                return mapRowToTask(rs);
            }
        });
    }


    @Override
    public Task getTaskById(int id) {
        log.info("SELECT * FROM tasks WHERE ID = ?");
        Task task;
        try {
            task = jdbcTemplate.queryForObject(
                    "SELECT * FROM tasks WHERE ID = ?",
                    new RowMapper<Task>() {
                        public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return mapRowToTask(rs);
                        }
                    },
                    id);
        } catch (EmptyResultDataAccessException e) {
//        throw new TaskNotFoundException("No such task in DB for given ID");
            throw new RuntimeException("No such task in DB for given ID");
        }
        return task;
    }

    @Override
    public void deleteTaskById(int id) {
        log.info("Running Spring JDBC query to DELETE");
        String sql = "DELETE FROM tasks WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    private Task mapRowToTask(ResultSet rs) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setStatus(rs.getString("status"));
        task.setPriority(rs.getString("priority"));
        task.setUser(userDAO.getUserById(rs.getInt("user_id")));
        return task;
    }
}
