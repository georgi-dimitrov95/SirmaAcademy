package com.football.api.service;

import com.football.api.model.*;
import com.football.api.model.Record;
import com.football.api.model.dto.PairDto;
import com.football.api.repository.jpa.PlayerJpaRepository;
import com.football.api.repository.jpa.RecordJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SharedMinutesService {

    @Autowired
    PlayerJpaRepository playerJpaRepository;

    @Autowired
    RecordJpaRepository recordJpaRepository;

//    gets all unique Pairs of players that have shared minutes together (not sorted)
    public List<Pair> getPairsWithSharedMinutes() {
        List<Player> players = playerJpaRepository.findAll();
        List<Pair> pairs = new ArrayList<>();

        for (Player playerA : players) {
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

                            Pair pair = new Pair();
                            pair.setA(playerA);
                            pair.setB(playerB);

//                            if there is already such a pair, add the shared minutes and matches to the pair in the list (avoids duplication of Pairs so that we don't end up with one pair (PlayerA, PlayerB) and another pair (PlayerB, PlayerA);
//                            couldn't make it work properly with a HashSet<Pair> pairs so I used List<Pair> pairs
                            for (Pair pairX : pairs) {
                                if (playerA == pairX.getA() && playerB == pairX.getB()) {
                                    pairX.getMinutesShared().add(shared);
                                    pairX.addMinutes(shared);
                                    pairX.getMatchesSharedById().add(match.getId());
                                    break;
                                }
                            }

//                            create new pair and add it to the list
                            if (!pairs.contains(pair)) {
                                pair.getMinutesShared().add(shared);
                                pair.addMinutes(shared);
                                pair.getMatchesSharedById().add(match.getId());
                                pairs.add(pair);
                            }
                        }
                    }
                }
            }
        }

        return pairs;
    }


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
