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

    public void csvToDatabase() {
        try {
            ArrayList<Match> matches = matchCsvReader.readFromCsv(TEAMS_FILE);
            matchJpaRepository.saveAll(matches);
        } catch (IOException e) {
            System.out.println("Something went wrong during the CSV reading process.");
            throw new RuntimeException(e);
        }
    }
}
