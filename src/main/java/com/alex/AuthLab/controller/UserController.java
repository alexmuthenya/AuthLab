package com.alex.AuthLab.controller;


import com.alex.AuthLab.model.User;
import com.alex.AuthLab.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());


    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
