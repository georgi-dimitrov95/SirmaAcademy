package com.football.api.controller;

import com.football.api.model.Player;
import com.football.api.model.Team;
import com.football.api.model.dto.TeamDto;
import com.football.api.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/csv")
    public String read() {
        try  {
            teamService.listToDatabase();
            return "Success";
        } catch (IOException e) {
            return "Could not read CSV file";
        }
    }

    @GetMapping("/get")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTeam(@PathVariable Long id) {
        try {
            TeamDto teamDto = teamService.getTeam(id);
            return ResponseEntity.ok(teamDto);
        } catch (EntityNotFoundException e) {
            String errorMessage = "Team not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
