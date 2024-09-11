package com.football.api.repository.csv;

import com.football.api.model.Match;
import com.football.api.model.Player;
import com.football.api.model.Team;
import com.football.api.repository.jpa.TeamJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class MatchCsvReader {

    @Autowired
    TeamJpaRepository teamJpaRepository;

    public ArrayList<Match> readFromCsv(String filePath) throws IOException {
        ArrayList<Match> matches = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

//                parse values from csv row
                Long id = Long.parseLong(fields[0]);
                Long aTeamId = Long.parseLong(fields[1]);
                Long bTeamId = Long.parseLong(fields[2]);
//                eventually parse to LocalDate
                String date = fields[3];
//                if penalties --> mins should be 120, not 90
                String score = (fields[4]);

//                find if teams with such IDs exist
                Team aTeam = teamJpaRepository.findById(aTeamId).orElseThrow(() -> new IllegalArgumentException("No team 'A' was found with ID: " + aTeamId));
                Team bTeam = teamJpaRepository.findById(bTeamId).orElseThrow(() -> new IllegalArgumentException("No team 'B' was found with ID: " + bTeamId));

//                create a match instance and add it to the list of matches
                Match match = new Match(id, aTeam, bTeam, date, score);
                matches.add(match);
            }
        }
        return matches;
    }
}
