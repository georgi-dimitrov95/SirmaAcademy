package com.example.restdbapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/hello")
    public String hello(@RequestBody String message) {
        System.out.println("@@@@@@@@@@@@@@@@");
        return "Success";
    }
}
