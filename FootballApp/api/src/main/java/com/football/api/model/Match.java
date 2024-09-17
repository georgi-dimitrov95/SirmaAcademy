package com.football.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Team A should not be null")
    private Team aTeam;

    @ManyToOne
    @JoinColumn(name="b_team_id")
    @NotNull(message = "Team B should not be null")
    private Team bTeam;

//    should be changed to a date class or go through further validation in the Service class
    @NotBlank(message = "The date should neither be null nor an empty string")
    private String date;

//    further validation in the Service class
    @NotBlank(message = "The score should neither be null nor an empty string")
    private String score;

    public Match(Team aTeam, Team bTeam, String[] fieldsRow) {
        this.aTeam = aTeam;
        this.bTeam = bTeam;
        this.date = fieldsRow[3];
        this.score = fieldsRow[4];
    }
}
