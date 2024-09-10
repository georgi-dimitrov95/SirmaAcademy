package com.football.api.controller;

import com.football.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/csv")
    public String read() {
        try  {
            teamService.csvToDatabase();
            return "Success";
        } catch (RuntimeException e) {
            return "Failed";
        }
    }
}
