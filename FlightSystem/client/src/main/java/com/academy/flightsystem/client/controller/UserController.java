package com.academy.flightsystem.client.controller;

import com.academy.flightsystem.client.model.User;
import com.academy.flightsystem.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.register(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("user", new User());
            model.addAttribute("error", "An error occurred during the registration.");
            return "register";
        }
    }

    @GetMapping("/login")
    // TODO add error and logout
    public String loginPage(Model model){
        return "login";
    }
}
