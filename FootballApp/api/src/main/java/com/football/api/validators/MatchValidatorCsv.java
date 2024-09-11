package com.football.api.validators;

public class MatchValidatorCsv implements ValidatorCsv{

    @Override
    public boolean dataIsValid(String[] fieldsRow) {

//        csv files for matches should have 5 columns
        if (fieldsRow.length != 5) {
            return false;
        }

        try {
            Long id = Long.parseLong(fieldsRow[0]);
            Long aID = Long.parseLong(fieldsRow[1]);
            Long bID = Long.parseLong(fieldsRow[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        String date = fieldsRow[3];
        String score = fieldsRow[4];

        return StringValidator.validateDate(date) && ScoreValidator.validateScore(score);
    }
}
