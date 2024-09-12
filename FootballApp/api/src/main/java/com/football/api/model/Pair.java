package com.football.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
public class Pair {

    private Player a;
    private Player b;

    ArrayList<Integer> minutesShared;
    private int totalMinutesShared;
    private HashSet<Long> matchesSharedById;

    public Pair() {
        this.minutesShared = new ArrayList<>();
        this.matchesSharedById = new HashSet<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair other = (Pair) obj;

        return (Objects.equals(a, other.getA()) && Objects.equals(b, other.getB())) ||
                (Objects.equals(a, other.getB()) && Objects.equals(b, other.getA()));
    }

    public int hashCode() {
        return Objects.hash(a) + Objects.hash(b);
    }

    public void addMinutes(int minutes) {
        totalMinutesShared += minutes;
    }
}

