package com.academy.flightsystem.api.controller;

import com.academy.flightsystem.api.model.User;
import com.academy.flightsystem.api.model.dto.RegisterUserDto;
import com.academy.flightsystem.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterUserDto userDto) {
        return authService.register(userDto);
    }
}
