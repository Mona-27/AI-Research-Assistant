package com.airesearch.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.airesearch.backend.dto.LoginRequest;
import com.airesearch.backend.dto.LoginResponse;
import com.airesearch.backend.dto.RegisterRequest;
import com.airesearch.backend.model.User;
import com.airesearch.backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "Auth Controller is Working!";
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        String token = userService.loginUser(request);

        return new LoginResponse(token);
    }
}