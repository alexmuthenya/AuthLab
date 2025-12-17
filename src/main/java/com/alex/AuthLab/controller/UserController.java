package com.alex.AuthLab.controller;


import com.alex.AuthLab.dto.ApiResponse;
import com.alex.AuthLab.model.User;
import com.alex.AuthLab.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody User user) {
        return userService.registerUser(user);


    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
