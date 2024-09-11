package com.football.api.validators;

public class TeamValidatorCsv implements ValidatorCsv {

    @Override
    public boolean dataIsValid(String[] fieldsRow) {

//        csv files for teams should have 4 columns
        if (fieldsRow.length != 4) {
            return false;
        }

//        skip the first element (containing ID) because it's not needed
        String name = fieldsRow[1];
        String manager = fieldsRow[2];
        String group = fieldsRow[3];

        return StringValidator.validateName(name) && StringValidator.validateName(manager) && StringValidator.validateGroup(group);
    }
}