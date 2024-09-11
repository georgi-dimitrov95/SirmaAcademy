package com.football.api.validators;

import java.sql.SQLOutput;

public class RecordValidatorCsv {

    public boolean dataIsValid(String[] fieldsRow) {

//        csv files for records should have 5 columns
        if (fieldsRow.length != 5) {
            return false;
        }

        try {
            Long id = Long.parseLong(fieldsRow[0]);
            Long playerId = Long.parseLong(fieldsRow[1]);
            Long matchId = Long.parseLong(fieldsRow[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        String fromMinutes = fieldsRow[3];
        String toMinutes = fieldsRow[4];

        return StringValidator.validateMinutes(fromMinutes) && StringValidator.validateMinutes(toMinutes);
    }
}
