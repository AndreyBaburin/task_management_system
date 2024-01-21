package com.andrey_baburin.controller;

import com.andrey_baburin.entity.Task;
import com.andrey_baburin.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @Mock
    private Model model;

    @InjectMocks
    private TaskController taskController;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task();
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(task, task);
        when(taskService.getAllTasks()).thenReturn(tasks);

        String viewName = taskController.getAllTasks(model);

        assertEquals("tasks", viewName);
        verify(model, times(1)).addAttribute("tasks", tasks);
    }

    @Test
    void testTaskById() {
        int taskId = 1;
        when(taskService.getTaskById(taskId)).thenReturn(task);

        String viewName = taskController.taskById(taskId, model);

        assertEquals("task", viewName);
        verify(model, times(1)).addAttribute("task", task);
    }

}