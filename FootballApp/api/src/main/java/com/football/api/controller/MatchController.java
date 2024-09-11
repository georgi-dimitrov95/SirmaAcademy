package com.football.api.controller;

import com.football.api.model.Match;
import com.football.api.model.Team;
import com.football.api.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getAll")
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }
}
