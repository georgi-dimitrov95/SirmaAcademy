package com.football.api.controller;

import com.football.api.model.Player;
import com.football.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/csv")
    public String read() {
        try  {
            playerService.listToDatabase();
            return "Success";
        } catch (IOException e) {
            return "Could not read CSV file";
        }
    }

    @GetMapping("/getAll")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
    
}
