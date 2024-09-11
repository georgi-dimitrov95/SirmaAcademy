package com.football.api.service;

import com.football.api.model.Match;
import com.football.api.repository.csv.MatchCsvReader;
import com.football.api.repository.jpa.MatchJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class MatchService {
    public static final String TEAMS_FILE = "src/main/resources/csv/matches.csv";

    @Autowired
    MatchJpaRepository matchJpaRepository;

    @Autowired
    MatchCsvReader matchCsvReader;

    public void listToDatabase() throws IOException {
        try {
            ArrayList<Match> matches = matchCsvReader.csvToList(TEAMS_FILE);
            matchJpaRepository.saveAll(matches);
        } catch (IOException e) {
            throw new IOException("Invalid data and/or file path");
        }
    }
}
