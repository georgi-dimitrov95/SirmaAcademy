package com.football.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pair {

    private Player a;
    private Player b;

    ArrayList<Integer> minutesShared;
    private int totalMinutesShared;

//    key is MatchID, array contains fromMinutes & toMinutes
//    private HashMap<Long, Integer> aMinutesPerMatch;
//    private HashMap<Long, Integer> bMinutesPerMatch;



//    public void setTotalMinutesShared() {
//        this.totalMinutesShared = sharedMinutesPerMatch.stream().mapToInt(Integer::intValue).sum();
//    }
//
//    public Pair(Player player1, Player player2) {
//        this.player1 = player1;
//        this.player2 = player2;
//        this.sharedMinutesPerMatch = new ArrayList<>();
}

