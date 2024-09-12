package com.football.api.model.dto;

import com.football.api.model.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {
    private String teamA;
    private String teamB;
    private String date;
    private String score;

    public MatchDto(Match match) {
        this.teamA = match.getATeam().getName();
        this.teamB = match.getBTeam().getName();
        this.date = match.getDate();
        this.score = match.getScore();
    }
}
