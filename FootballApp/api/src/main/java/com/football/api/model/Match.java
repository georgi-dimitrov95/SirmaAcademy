package com.football.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="a_team_id")
    private Team aTeam;

    @ManyToOne
    @JoinColumn(name="b_team_id")
    private Team bTeam;

    private String date;
    private String score;

    public Match(Team aTeam, Team bTeam, String[] fieldsRow) {
        this.aTeam = aTeam;
        this.bTeam = bTeam;
        this.date = fieldsRow[3];
        this.score = fieldsRow[4];
    }
}
