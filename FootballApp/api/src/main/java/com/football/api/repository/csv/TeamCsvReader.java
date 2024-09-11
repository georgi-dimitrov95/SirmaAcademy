package com.football.api.repository.csv;

import com.football.api.model.Team;
import com.football.api.validators.TeamValidatorCsv;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class TeamCsvReader extends CsvReader<Team> {

    @Override
    public ArrayList<Team> csvToList(String filePath) throws IOException {
        ArrayList<Team> teams = new ArrayList<>();
        try {
            List<String[]> rows = this.readFromCSV(filePath);
            TeamValidatorCsv teamValidatorCsv = new TeamValidatorCsv();
            boolean invalidRows = true;

            for (String[] fieldsRow : rows) {
//                logs every row of invalid data
                if (!teamValidatorCsv.dataIsValid(fieldsRow)) {
                    logger.error("Invalid data in row containing values: {}", Arrays.toString(fieldsRow));
                    invalidRows = false;
                    continue;
                }

                Team team = new Team(fieldsRow);
                teams.add(team);
            }
//            throws an exception after parsing the data from each csv row
            if (!invalidRows) {
                throw new IllegalArgumentException("Rows found with invalid data.");
            }
        } catch (IOException | IllegalArgumentException e) {
            logger.error("Invalid data in file with path: {}", filePath, e);
            throw new IOException(e);
        }
        return teams;
    }
}