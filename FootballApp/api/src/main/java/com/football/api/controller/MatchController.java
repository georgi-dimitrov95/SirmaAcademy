package com.football.api.controller;

import com.football.api.model.Match;
import com.football.api.model.Team;
import com.football.api.model.dto.MatchDto;
import com.football.api.service.MatchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/csv")
    public String read() {
        try  {
            matchService.listToDatabase();
            return "Success";
        } catch (IOException e) {
            return "Could not read CSV file";
        }
    }

    @GetMapping("/get")
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMatch(@PathVariable Long id) {
        try {
            MatchDto matchDto = matchService.getMatch(id);
            return ResponseEntity.ok(matchDto);
        } catch (EntityNotFoundException e) {
            String errorMessage = "Match not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
