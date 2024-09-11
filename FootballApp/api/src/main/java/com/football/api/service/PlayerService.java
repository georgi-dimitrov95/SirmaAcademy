package com.football.api.service;

import com.football.api.model.Player;
import com.football.api.repository.csv.PlayerCsvReader;
import com.football.api.repository.jpa.PlayerJpaRepository;
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

    public List<Player> getAllPlayers() {
        return playerJpaRepository.findAll();
    }

}
