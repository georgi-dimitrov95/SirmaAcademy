package com.football.api.service;

import com.football.api.model.*;
import com.football.api.model.Record;
import com.football.api.repository.jpa.MatchJpaRepository;
import com.football.api.repository.jpa.PlayerJpaRepository;
import com.football.api.repository.jpa.RecordJpaRepository;
import com.football.api.repository.jpa.TeamJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SharedMinutesService {

    @Autowired
    PlayerJpaRepository playerJpaRepository;

    @Autowired
    TeamJpaRepository teamJpaRepository;

    @Autowired
    MatchJpaRepository matchJpaRepository;

    @Autowired
    RecordJpaRepository recordJpaRepository;


    public List<Record> getPairWithMostSharedMinutes() {
        List<Player> players = playerJpaRepository.findAll();
        List<Match> matches = matchJpaRepository.findAll();
        List<Record> records = recordJpaRepository.findAll();
        Set<Pair> pairs = new HashSet<>();

        for (Player playerA : players) {

//            put playerA in a pair
            Pair pair = new Pair();
            pair.setA(playerA);

            List<Record> playerRecords = recordJpaRepository.findByPlayerId(playerA.getId());

//            go through all records of playerA
            for (Record record : playerRecords) {
//                minutes range of playerA
                int aFromMinutes = record.getFromMinutes();
                int aToMinutes = record.getToMinutes();

                Match match = record.getMatch();
                List<Record> recordsFromSameMatch = recordJpaRepository.findByMatchId(match.getId());

//                go through all records from the same match
                for (Record recordInCurrentMatch : recordsFromSameMatch) {
                    Player playerB = recordInCurrentMatch.getPlayer();
                    Long bId = playerB.getId();
                    String bTeam = playerB.getTeam().getName();

//                    check if playerB has different Id and is a teammate of playerA
                    if (!Objects.equals(bId, playerA.getId()) && Objects.equals(bTeam, playerA.getTeam().getName())) {
//                        minutes range of playerB
                        int bFromMinutes = recordInCurrentMatch.getFromMinutes();
                        int bToMinutes = recordInCurrentMatch.getToMinutes();

//                        calculate minutes if they overlap
                        if ((aFromMinutes <= bToMinutes) && (aToMinutes >= bFromMinutes)) {
                            int shared = Math.min(aToMinutes, bToMinutes) - Math.max(aFromMinutes, bFromMinutes);

                            pair.setB(playerB);
                            pair.getMinutesShared().add(shared);
                            pairs.add(pair);
                        }
                    }
                }
            }
        }
//        adding this return because i am turning in the project, will finish it after 00:00
        return records;
    }
}
