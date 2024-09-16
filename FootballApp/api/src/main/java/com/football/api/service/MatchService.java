package com.football.api.service;

import com.football.api.model.Match;
import com.football.api.model.dto.MatchDto;
import com.football.api.repository.csv.MatchCsvReader;
import com.football.api.repository.jpa.MatchJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {
    public static final String TEAMS_FILE = "src/main/resources/csv/matches.csv";

    @Autowired
    MatchJpaRepository matchJpaRepository;

    @Autowired
    MatchCsvReader matchCsvReader;

    public void listToDatabase() throws IOException {
        try {
            ArrayList<Match> matches = matchCsvReader.csvToList(TEAMS_FILE);
            matchJpaRepository.saveAll(matches);
        } catch (IOException e) {
            throw new IOException("Invalid data and/or file path");
        }
    }

    public List<Match> getAllMatches() {
        return matchJpaRepository.findAll();
    }

    public MatchDto getMatch(Long id) {
        Match match = matchJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);;
        return new MatchDto(match);
    }

    public Match addMatch(Match match) {
        return matchJpaRepository.save(match);
    }

    public void deleteMatch(Long id) {
        if (matchJpaRepository.existsById(id)) {
            matchJpaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
