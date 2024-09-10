package com.football.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int teamNumber;
    private String position;
    private String fullName;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    public Player(String[] fieldsRow) {
        this.teamNumber = Integer.parseInt(fieldsRow[1]);
        this.position = fieldsRow[2];
        this.fullName = fieldsRow[3];
    }
}
