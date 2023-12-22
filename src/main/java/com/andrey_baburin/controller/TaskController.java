package com.andrey_baburin.controller;

import com.andrey_baburin.entity.Task;
import com.andrey_baburin.entity.User;
import com.andrey_baburin.security.UserInformation;
import com.andrey_baburin.services.TaskService;
import com.andrey_baburin.services.UserService;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/manager")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

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
    public String create(@Valid @ModelAttribute Task task, @AuthenticationPrincipal UserInformation userInformation) {
        User creator = userInformation.getUser();
        task.setCreator(creator);
        taskService.createTask(task);
        return "redirect:/manager/tasks";
    }

    @GetMapping("/tasks/{id}/edit")
    public String edit(@PathVariable int id, Model model,  @AuthenticationPrincipal UserInformation userInformation) throws AccessDeniedException {
        Task task = taskService.getTaskById(id);
        if (task.getCreator().equals(userInformation.getUser())){
            List<User> users = userService.getAllUsers();
            model.addAttribute("task", task);
            model.addAttribute("users", users);
            return "editForCreator";
        } else if (task.getUser().equals(userInformation.getUser())) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("task", task);
            model.addAttribute("users", users);
            return "editForUser";
        }
        throw new AccessDeniedException("Нет прав доступа");
    }

    @PutMapping("/tasks/{id}")
    public String doEdit(@Valid @ModelAttribute Task task, @PathVariable int id) {
        taskService.updateTask(task.getId(), task);
        return "redirect:/manager/tasks";
    }

    @DeleteMapping("/tasks/{id}")
    public String delete(@PathVariable int id) {
        taskService.deleteById(id);
        return "redirect:/manager/tasks";
    }
}
