package com.football.api.validators;

import java.util.ArrayList;
import java.util.Arrays;

public class StringValidator {

    public static boolean validateName(String name) {

//        name can contain unicode characters, ', -, (captain)
        String regex2a = "^[\\p{L} `'’-]+(\\p{Zs}\\(\\p{L}+\\))?$";
        return name.matches(regex2a);
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

//    checks for double and triple digits because of such cases like the one linked below...
//    https://en.wikipedia.org/wiki/AS_Adema_149%E2%80%930_SO_l%27Emyrne
    public static boolean validateScore(String score) {
        String regex = "^\\d{1,3}-\\d{1,3}$";
        return score.matches(regex);
    }
}
