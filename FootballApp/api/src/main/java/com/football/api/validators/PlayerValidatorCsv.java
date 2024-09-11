package com.football.api.validators;

import java.util.Arrays;

public class PlayerValidatorCsv implements ValidatorCsv{

    @Override
    public boolean dataIsValid(String[] fieldsRow) {

//        csv files for teams should have 5 columns
        if (fieldsRow.length != 5) {
            return false;
        }

//        skip the first element (containing ID) because it's not needed
        try {
            int teamNumber = Integer.parseInt(fieldsRow[1]);
            Long teamId = Long.parseLong(fieldsRow[4]);

        } catch (NumberFormatException e) {
            return false;
        }

        String position = fieldsRow[2];
        String fullName = fieldsRow[3];

        return StringValidator.validateName(position) && StringValidator.validateName(fullName);
    }
}
