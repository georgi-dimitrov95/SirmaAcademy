package com.football.api.service;

import com.football.api.model.Team;
import com.football.api.repository.csv.TeamCsvReader;
import com.football.api.repository.jpa.TeamJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class TeamService {
    public static final String TEAMS_FILE = "src/main/resources/csv/teams.csv";

    @Autowired
    TeamJpaRepository teamJpaRepository;

    @Autowired
    TeamCsvReader teamCsvReader;

    public void listToDatabase() throws IOException {
        try {
            ArrayList<Team> teams = teamCsvReader.csvToList(TEAMS_FILE);
            teamJpaRepository.saveAll(teams);
        } catch (IOException e) {
            throw new IOException("Invalid data and/or file path");
        }
    }
}
