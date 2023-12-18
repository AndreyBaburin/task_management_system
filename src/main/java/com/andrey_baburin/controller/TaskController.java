package com.andrey_baburin.controller;

import com.andrey_baburin.entity.Task;
import com.andrey_baburin.entity.User;
import com.andrey_baburin.services.TaskService;
import com.andrey_baburin.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/manager")
public class TaskController {

    private final TaskService taskService;
    private  final UserService userService;

    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/task")
    public String taskById(@RequestParam("id") int id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task";
    }

    @GetMapping("tasks/{id}")
    public String showTaskById(@PathVariable int id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task";
    }

    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("users", userService.getAllUsers());
        return "new";
    }

    @PostMapping("/tasks")
    public String create(@Valid @ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/manager/tasks";
    }

    @GetMapping("/tasks/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Task task = taskService.getTaskById(id);
        List<User> users = userService.getAllUsers();
        model.addAttribute("task", task);
        model.addAttribute("users", users);
        return "edit";
    }

    @PutMapping("/tasks/{id}")
    public String doEdit(@Valid @ModelAttribute Task task, @PathVariable int id) {
        taskService.updateTask(task.getId(),task);
        return "redirect:/manager/tasks";
    }

    @DeleteMapping("/tasks/{id}")
    public String delete(@PathVariable int id) {
        taskService.deleteById(id);
        return "redirect:/manager/tasks";
    }
}
