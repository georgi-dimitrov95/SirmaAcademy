package com.football.api.model.dto;

import com.football.api.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private String name;
    private String manager;

    public TeamDto(Team team) {
        this.name = team.getName();
        this.manager = team.getManager();
    }
}
