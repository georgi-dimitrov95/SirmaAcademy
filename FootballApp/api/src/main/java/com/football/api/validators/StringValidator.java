package com.football.api.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {

    public static boolean validateName(String name) {

//        name can contain unicode characters, ', -, (captain)
        String regex = "^[\\p{L} `'’-]+(\\p{Zs}\\(\\p{L}+\\))?$";
        return name.matches(regex);
    }

//    in tournaments the group can only be a single uppercase letter
//    in leagues (e.g. Premier League) there are no groups, so it can be an empty string
    public static boolean validateGroup(String group) {
        return (group.matches("^[A-Z]$") || group.isEmpty());
    }

//    the shortest way to reference a position in football is by using two capital letters
    public static boolean validatePosition(String position) {
        ArrayList<String> positions = new ArrayList<>(Arrays.asList("GK", "DF", "MF", "FW"));
        return positions.contains(position);
    }

//    used in validateDate method
    private static ArrayList<String> getDateFormats() {
        ArrayList<String> validDateFormats = new ArrayList<>();
        validDateFormats.add("M/d/uuuu");
        validDateFormats.add("M/dd/uuuu");
        validDateFormats.add("M/D/uuuu");
        validDateFormats.add("MM/d/uuuu");
        validDateFormats.add("MM/D/uuuu");
        validDateFormats.add("MM/dd/uuuu");

        validDateFormats.add("MM-d-uuuu");
        validDateFormats.add("MM-dd-uuuu");
        validDateFormats.add("M-d-uuuu");
        validDateFormats.add("M-dd-uuuu");
        return validDateFormats;
    }

    public static boolean validateDate(String date) {
        ArrayList<String> validDateFormats = getDateFormats();

//        checks if the date matches a valid date format
        for (String dateFormat : validDateFormats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat).withResolverStyle(ResolverStyle.STRICT);
            try {
                LocalDate.parse(date.trim(), formatter);
                return true;
            } catch (DateTimeParseException ignored) {}
        }
        return false;
    }

//    checks if the string is a number in a valid format between 1-120 or "NULL"
    public static boolean validateMinutes(String minutes) {
        String regex = "^(0|1[0-1][0-9]|120|[1-9][0-9]?|NULL)$";
        return minutes.matches(regex);
    }
}
