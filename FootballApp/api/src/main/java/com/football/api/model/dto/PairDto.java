package com.football.api.model.dto;

import com.football.api.model.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PairDto {
    private String playerA;
    private String playerB;
    private ArrayList<Integer> minutesSharedPerMatch;
    private int totalMinutesShared;

    public PairDto(Pair pair) {
        this.playerA = pair.getA().getFullName();
        this.playerB = pair.getB().getFullName();
        this.minutesSharedPerMatch = pair.getMinutesShared();
        this.totalMinutesShared = pair.getTotalMinutesShared();
    }
}
