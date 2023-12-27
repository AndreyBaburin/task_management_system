package com.andrey_baburin.data;

import com.andrey_baburin.entity.Task;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskJdbcDAOTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TaskJdbcDAO taskJdbcDAO;

    public TaskJdbcDAOTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTasks_ShouldReturnAllTasks() {
        List<Task> expectedTasks = new ArrayList<>();
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedTasks);
        List<Task> tasks = taskJdbcDAO.getAllTasks();
        assertEquals(expectedTasks, tasks);
    }

    @Test
    public void getTaskById_ShouldReturnTaskWithGivenId() {
        int taskId = 1;
        Task expectedTask = new Task();
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt())).thenReturn(expectedTask);
        Task task = taskJdbcDAO.getTaskById(taskId);
        assertEquals(expectedTask, task);
    }



}