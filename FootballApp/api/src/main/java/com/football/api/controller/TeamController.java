package com.football.api.controller;

import com.football.api.model.Team;
import com.football.api.model.dto.TeamDto;
import com.football.api.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/add")
    public ResponseEntity<Team> addTeam(@Valid @RequestBody Team team) {
        Team savedTeam = teamService.addTeam(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> putTeam(@PathVariable Long id, @Valid @RequestBody Team updateTeam) {
        try {
            Team team = teamService.updateTeam(id, updateTeam);
            return ResponseEntity.ok(team);
        } catch (EntityNotFoundException e) {
            String errorMessage = "Team not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        try {
            teamService.deleteTeam(id);
            String successMessage = "Successfully deleted team with ID: " + id;
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (EntityNotFoundException e) {
            String errorMessage = "Team not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
