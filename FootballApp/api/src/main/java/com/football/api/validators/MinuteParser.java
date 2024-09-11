package com.football.api.validators;

import java.util.Objects;

public class MinuteParser {

    //    if fromMinutes is NULL we consider it to be 0
    public static int fromMinutesParse(String minutes) {
        int fromMinutes;
        if (Objects.equals(minutes, "NULL")) {
            fromMinutes = 0;
        } else {
            fromMinutes = Integer.parseInt(minutes);
        }
        return fromMinutes;
    }

    //    check for penalties
    public static int toMinutesParse(String minutes, String score) {
        int toMinutes;

        if (ScoreValidator.validateScoreWithPenalties(score)) {
            if (Objects.equals(minutes, "NULL")) {
                toMinutes = 120;
            } else {
                toMinutes = Integer.parseInt(minutes);
            }
        } else {
            if (Objects.equals(minutes, "NULL")) {
                toMinutes = 90;
            } else {
                toMinutes = Integer.parseInt(minutes);
            }
        }
        return toMinutes;
    }
}
