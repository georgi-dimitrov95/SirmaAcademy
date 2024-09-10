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

//    eventually convert to LocalDate
    private String date;
    private String score;
}
