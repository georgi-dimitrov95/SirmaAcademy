package com.football.api.controller;

import com.football.api.service.MatchService;
import com.football.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/csv")
    public String read() {
        try  {
            matchService.csvToDatabase();
            return "Success";
        } catch (RuntimeException e) {
            return "Failed";
        }
    }
}
