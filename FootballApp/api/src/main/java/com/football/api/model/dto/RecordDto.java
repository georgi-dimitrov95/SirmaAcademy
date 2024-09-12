package com.football.api.model.dto;

import com.football.api.model.Record;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {
    private String player;
    private String match;
    private int fromMinutes;
    private int toMinutes;

    public RecordDto(Record record) {
        this.player = record.getPlayer().getFullName();
        this.match = record.getMatch().getATeam().getName() + record.getMatch().getBTeam().getName();
        this.fromMinutes = record.getFromMinutes();
        this.toMinutes = record.getToMinutes();
    }
}
