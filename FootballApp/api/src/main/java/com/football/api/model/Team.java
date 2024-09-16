package com.football.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "The name of the team should neither be null nor an empty string")
    @Pattern(regexp = "^'?\\p{L}+(['`’ -]\\p{L}+)*(\\p{Zs}\\(\\p{L}+\\))?$", message = "Invalid team name")
    private String name;

    @NotBlank(message = "The name of the manager should neither be null nor an empty string")
    @Pattern(regexp = "^'?\\p{L}+(['`’ -]\\p{L}+)*(\\p{Zs}\\(\\p{L}+\\))?$", message = "Invalid manager name")
    private String manager;

    @Pattern(regexp = "^[A-Z]?$", message = "The group can be either a single uppercase letter or just an empty string")
    private String tournamentGroup;

    public Team(String[] fieldsRow) {
        this.id = Long.parseLong(fieldsRow[0]);
        this.name = fieldsRow[1];
        this.manager = fieldsRow[2];
        this.tournamentGroup = fieldsRow[3];
    }
}
