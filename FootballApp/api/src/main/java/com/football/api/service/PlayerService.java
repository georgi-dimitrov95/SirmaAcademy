package com.football.api.service;

import com.football.api.model.Player;
import com.football.api.model.dto.PlayerDto;
import com.football.api.repository.csv.PlayerCsvReader;
import com.football.api.repository.jpa.PlayerJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    public static final String PLAYERS_FILE = "src/main/resources/csv/players.csv";

    @Autowired
    PlayerJpaRepository playerJpaRepository;

    @Autowired
    PlayerCsvReader playerCsvReader;

    public void listToDatabase() throws IOException {
        try {
            ArrayList<Player> players = playerCsvReader.csvToList(PLAYERS_FILE);
            playerJpaRepository.saveAll(players);
        } catch (IOException e) {
            throw new IOException("Invalid data and/or file path");
        }
    }

    public void deletePlayer(Long id) {
        if (playerJpaRepository.existsById(id)) {
            playerJpaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Player addPlayer(Player player) {
        return playerJpaRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerJpaRepository.findAll();
    }

    public PlayerDto getPlayer(Long id) {
        Player player = playerJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new PlayerDto(player);
    }
}
