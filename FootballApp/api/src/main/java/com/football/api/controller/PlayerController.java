package com.football.api.controller;

import com.football.api.model.Player;
import com.football.api.model.dto.PlayerDto;
import com.football.api.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/get")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable Long id) {
        try {
            PlayerDto playerDto = playerService.getPlayer(id);
            return ResponseEntity.ok(playerDto);
        } catch (EntityNotFoundException e) {
            String errorMessage = "Player not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
