package com.football.api.repository.csv;

import com.football.api.model.Match;
import com.football.api.model.Player;
import com.football.api.model.Record;
import com.football.api.repository.jpa.MatchJpaRepository;
import com.football.api.repository.jpa.PlayerJpaRepository;
import com.football.api.validators.MinuteParser;
import com.football.api.validators.RecordValidatorCsv;
import com.football.api.validators.StringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class RecordCsvReader extends CsvReader<Record> {
    
    @Autowired
    PlayerJpaRepository playerJpaRepository;
    
    @Autowired
    MatchJpaRepository matchJpaRepository;

    public ArrayList<Record> csvToList(String filePath) throws IOException {
        ArrayList<Record> records = new ArrayList<>();
        
        try {
            List<String[]> rows = this.readFromCSV(filePath);
            RecordValidatorCsv recordValidatorCsv = new RecordValidatorCsv();
            boolean validRows = true;

//            if every element has valid data, turns it into an entity
//            if not, logs and propagates the exceptions but doesn't turn any of the valid rows into entities
            for (String[] fieldsRow : rows) {
                if (!recordValidatorCsv.dataIsValid(fieldsRow)) {
                    logger.error("Invalid data in row containing values: {}", Arrays.toString(fieldsRow));
                    System.out.println("ot tuk li e");
                    validRows = false;
                    continue;
                }

//                parse values needed for further validation
                Long id = Long.parseLong(fieldsRow[0]);
                Long playerId = Long.parseLong(fieldsRow[1]);
                Long matchId = Long.parseLong(fieldsRow[2]);
                String stringFromMinutes = fieldsRow[3];
                String stringToMinutes = fieldsRow[4];

//                find if a player and a match with such IDs exist
                Player player = playerJpaRepository.findById(playerId).orElseThrow(() -> new IllegalArgumentException("No player was found with ID: " + playerId));
                Match match = matchJpaRepository.findById(matchId).orElseThrow(() -> new IllegalArgumentException("No match was found with ID: " + matchId));



                String score = match.getScore();
                int fromMinutes = MinuteParser.fromMinutesParse(stringFromMinutes);
                int toMinutes = MinuteParser.toMinutesParse(stringToMinutes, score);

                if (fromMinutes >= toMinutes) {
                    logger.error("toMinutes is less than fromMinutes in row containing values: {}", Arrays.toString(fieldsRow));
                    validRows = false;
                    continue;
                }

//                create a match instance and add it to the list of records
                Record record = new Record(id, player, match, fromMinutes, toMinutes);
                records.add(record);
            }

            if (!validRows) {
                throw new IllegalArgumentException("Rows found with invalid data.");
            }
        } catch (IOException e) {
            logger.error("Error in reading file with path: {}", filePath, e);
            throw new IOException(e);
        } catch (NumberFormatException e) {
            logger.error("Invalid number format in file with path: {}", filePath, e);
            throw new IOException(e);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid data in file with path: {}", filePath, e);
            throw new IOException(e);
        }

        return records;
    }
}
