package com.football.api.model.dto;

import com.football.api.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private String name;
    private String position;
    private String team;

    public PlayerDto(Player player) {
        this.name = player.getFullName();
        this.position = player.getPosition();
        this.team = player.getTeam().getName();
    }
}




