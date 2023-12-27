package com.andrey_baburin.services;

import com.andrey_baburin.data.TaskDAO;
import com.andrey_baburin.entity.Task;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class TaskProcessor implements TaskService {

    private final TaskDAO taskDAO;

    public TaskProcessor(@Qualifier("JDBC") TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }


    @Override
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = taskDAO.getTaskById(id);
        if (task == null) {
            throw new RuntimeException("The task was not found in the database by ID");
        }
        return task;
    }

    @Override
    public void createTask(Task task) {
        taskDAO.addTask(task);
    }

    @Override
    public void updateTask(int id,Task task) {
        task.setId(id);
        taskDAO.updateTask(task);
    }

    @Override
    public void deleteById(int id) {
        taskDAO.deleteTaskById(id);
    }
}
