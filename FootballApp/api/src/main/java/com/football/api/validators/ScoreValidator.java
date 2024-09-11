package com.football.api.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreValidator {

    public static boolean validateScore(String score) {
        return validateScoreWithoutPenalties(score) || validateScoreWithPenalties(score);
    }

    //    checks for double and triple digits because of such cases like the one linked           below...
//    https://en.wikipedia.org/wiki/AS_Adema_149%E2%80%930_SO_l%27Emyrne
    public static boolean validateScoreWithoutPenalties(String score) {
        return score.matches("^\\d{1,3}-\\d{1,3}$");
    }

    //    matches scores with penalties and checks if the numbers of goals are correct
    public static boolean validateScoreWithPenalties(String score) {
        String regex = "^(\\d{1,3})\\((\\d{1,3})\\)-(\\d{1,3})\\((\\d{1,3})\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(score);

        if (matcher.matches()) {
//            matches each number from the score because in penalties aGoals = bGoals and aPens != bPens
            String aGoals = matcher.group(1);
            String aPens = matcher.group(2);
            String bGoals = matcher.group(3);
            String bPens = matcher.group(4);

//            no need to turn them into ints here
            return aGoals.equals(bGoals) && !aPens.equals(bPens);
        }
        return false;
    }
}
