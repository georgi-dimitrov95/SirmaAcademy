package com.football.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="player_id")
    @NotNull(message = "Player should not be null")
    private Player player;

    @ManyToOne
    @JoinColumn(name="match_id")
    @NotNull(message = "Match should not be null")
    private Match match;

//    further validation for both minutes fields in the Service class is needed
    @Min(value = 0, message = "Starting minutes should not be less than 0")
    @Max(value = 120, message = "Starting minutes should not be greater than 120")
    private int fromMinutes;

    @Min(value = 1, message = "End minutes should not be less than 1")
    @Max(value = 120, message = "End minutes should not be greater than 120")
    private int toMinutes;
}
