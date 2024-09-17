package com.football.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @Min(value = 0, message = "Team number should not be less than 0")
    @Max(value = 99, message = "Team number should not be greater than 99")
    private int teamNumber;

    @NotBlank(message = "The position should neither be null nor an empty string")
    @Pattern(regexp = "^(GK|DF|MF|FW)$", message = "Invalid position. Allowed ones: GK, DF, MF, FW")
    private String position;

    @NotBlank(message = "The name of the player should neither be null nor an empty string")
    @Pattern(regexp = "^'?\\p{L}+(['`’ -]\\p{L}+)*(\\p{Zs}\\(\\p{L}+\\))?$", message = "Invalid player name")
    private String fullName;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    public Player(String[] fieldsRow, Team team) {
        this.teamNumber = Integer.parseInt(fieldsRow[1]);
        this.position = fieldsRow[2];
        this.fullName = fieldsRow[3];
        this.team = team;
    }
}
