package com.andrey_baburin.controller;

import com.andrey_baburin.entity.User;
import com.andrey_baburin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/registration")
    public String RegistrationPage(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration (@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(userService.exist(user)) {
            bindingResult.rejectValue("email", "error.user", "Пользователь уже существует");
            return "registration";
        }

        if(bindingResult.hasErrors()) {
            return "registration";
        }

        userService.createUser(user);
        return "redirect:/auth/login";
    }
}
