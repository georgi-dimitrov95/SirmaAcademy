package com.football.api.repository.csv;

import com.football.api.model.Player;
import com.football.api.model.Team;
import com.football.api.repository.jpa.TeamJpaRepository;
import com.football.api.validators.PlayerValidatorCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PlayerCsvReader extends CsvReader<Player> {

    @Autowired
    TeamJpaRepository teamJpaRepository;

    public ArrayList<Player> csvToList(String filePath) throws IOException {
        ArrayList<Player> players = new ArrayList<>();
        try {
            List<String[]> rows = this.readFromCSV(filePath);
            PlayerValidatorCsv playerValidatorCsv = new PlayerValidatorCsv();
            boolean invalidRows = true;

            for (String[] fieldsRow : rows) {
//                logs every row of invalid data
                if (!playerValidatorCsv.dataIsValid(fieldsRow)) {
                    logger.error("Invalid data in row containing values: {}", Arrays.toString(fieldsRow));
                    invalidRows = false;
                    continue;
                }
                Long teamId = Long.parseLong(fieldsRow[4]);

//                find if a team with such an id exists
                Team team = teamJpaRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("No team was found with ID: " + teamId));

//                create a player instance and add it to the list of players
                Player player = new Player(fieldsRow, team);
                players.add(player);
            }

//            throws an exception after parsing the data from each csv row
            if (!invalidRows) {
                throw new IllegalArgumentException("Rows found with invalid data.");
            }
        } catch (IOException | IllegalArgumentException e) {
            logger.error("Invalid data in file with path: {}", filePath, e);
            throw new IOException(e);
        }
        return players;
    }
}
