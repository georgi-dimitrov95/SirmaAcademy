package com.football.api.controller;

import com.football.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/csv")
    public String read() {
        try  {
            playerService.csvToDatabase();
            return "Success";
        } catch (RuntimeException e) {
            return "Failed";
        }
    }
}
