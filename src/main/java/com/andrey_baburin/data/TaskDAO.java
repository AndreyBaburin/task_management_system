package com.andrey_baburin.data;

import com.andrey_baburin.entity.Task;

import java.util.List;

public interface TaskDAO {
    void addTask(Task t);

    List<Task> getAllTasks();

    Task getTaskById(int id);

    int updateTask(Task t);

    void deleteTaskById(int id);
}
