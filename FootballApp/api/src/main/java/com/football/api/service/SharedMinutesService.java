package com.football.api.service;

import com.football.api.model.*;
import com.football.api.model.Record;
import com.football.api.model.dto.PairDto;
import com.football.api.repository.jpa.PlayerJpaRepository;
import com.football.api.repository.jpa.RecordJpaRepository;
import com.football.api.utils.SharedMinutesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SharedMinutesService {

    @Autowired
    PlayerJpaRepository playerJpaRepository;

    @Autowired
    RecordJpaRepository recordJpaRepository;

//    gets all unique Pairs of players that have shared minutes together (not sorted)
//    for variable 'pairs' I couldn't make it work properly with a HashSet<Pair>, so I used List<Pair>
    public List<Pair> getPairsWithSharedMinutes() {
        List<Player> players = playerJpaRepository.findAll();
        List<Pair> pairs = new ArrayList<>();

        for (Player playerA : players) {
            List<Record> playerRecords = recordJpaRepository.findByPlayerId(playerA.getId());

//            go through all records of playerA
            for (Record record : playerRecords) {
                Long matchId = record.getMatch().getId();
                List<Record> recordsFromSameMatch = recordJpaRepository.findByMatchId(matchId);

//                go through all records from the same match
                for (Record recordInCurrentMatch : recordsFromSameMatch) {
                    SharedMinutesUtil.processPairMinutesForMatch(pairs, record, recordInCurrentMatch, matchId);
                }
            }
        }

        return pairs;
    }


//    this method and the one below are needed because there can be more than one Pair with max shared minutes (this is the case with the whole English team according to the csv data)
    public List<Pair> getPairsWithMostMinutesShared() {
        List<Pair> pairs = getPairsWithSharedMinutes();

//        find the maximum shared minutes
        int maxMinutes = pairs.stream()
                .max(Comparator.comparingInt(Pair::getTotalMinutesShared))
                .map(Pair::getTotalMinutesShared)
                .orElse(0);

//        return a list of all pairs with the maximum shared minutes
        return pairs.stream()
                .filter(pair -> pair.getTotalMinutesShared() == maxMinutes)
                .toList();
    }

//    convert all Pairs with max minutes to PairDTOs
    public List<PairDto> pairDTOsWithMostMinutesShared() {
        List<Pair> pairs = getPairsWithMostMinutesShared();
        List<PairDto> pairDTOs = new ArrayList<>();

        for (Pair pair : pairs) {
            PairDto pairDto = new PairDto(pair);
            pairDTOs.add(pairDto);
        }
        return pairDTOs;
    }
}
