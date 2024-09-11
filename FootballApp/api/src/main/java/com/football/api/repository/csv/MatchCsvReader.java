package com.football.api.repository.csv;

import com.football.api.model.Match;
import com.football.api.model.Team;
import com.football.api.repository.jpa.TeamJpaRepository;
import com.football.api.validators.MatchValidatorCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MatchCsvReader extends CsvReader<Match> {

    @Autowired
    TeamJpaRepository teamJpaRepository;

    public ArrayList<Match> csvToList(String filePath) throws IOException {
        ArrayList<Match> matches = new ArrayList<>();

        try {
            List<String[]> rows = this.readFromCSV(filePath);
            MatchValidatorCsv matchValidatorCsv = new MatchValidatorCsv();
            boolean validRows = true;

            for (String[] fieldsRow : rows) {
                if (!matchValidatorCsv.dataIsValid(fieldsRow)) {
                    logger.error("Invalid data in row containing values: {}", Arrays.toString(fieldsRow));
                    validRows = false;
                    continue;
                }

//                parse values needed for further validation
                Long aTeamId = Long.parseLong(fieldsRow[1]);
                Long bTeamId = Long.parseLong(fieldsRow[2]);

//                find if teams with such IDs exist
                Team aTeam = teamJpaRepository.findById(aTeamId).orElseThrow(() -> new IllegalArgumentException("No team 'A' was found with ID: " + aTeamId));
                Team bTeam = teamJpaRepository.findById(bTeamId).orElseThrow(() -> new IllegalArgumentException("No team 'B' was found with ID: " + bTeamId));

//                create a match instance and add it to the list of matches
                Match match = new Match(aTeam, bTeam, fieldsRow);
                matches.add(match);
            }

//            throws an exception after parsing the data from each csv row
            if (!validRows) {
                throw new IllegalArgumentException("Rows found with invalid data.");
            }
        } catch (IOException | IllegalArgumentException e) {
            logger.error("Invalid data in file with path: {}", filePath, e);
            throw new IOException(e);
        }
        return matches;
    }
}
