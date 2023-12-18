package com.andrey_baburin.services;

import com.andrey_baburin.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(int id);

    void createTask(Task t);

    void updateTask(int id,Task task);

    void deleteById(int id);
}
