package com.football.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String manager;
    private String tournamentGroup;

    public Team(String[] fieldsRow) {
        this.id = Long.parseLong(fieldsRow[0]);
        this.name = fieldsRow[1];
        this.manager = fieldsRow[2];
        this.tournamentGroup = fieldsRow[3];
    }
}
