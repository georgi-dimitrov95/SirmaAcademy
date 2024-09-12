package com.football.api.utils;

import com.football.api.model.Pair;
import com.football.api.model.Player;
import com.football.api.model.Record;

import java.util.List;
import java.util.Objects;

public class SharedMinutesUtil {

//    add the pair to the list of pairs if the list doesn't contain it
    public static void addNewPairToList(List<Pair> pairs, Pair pair, Long id, int minutes) {
        if (!pairs.contains(pair)) {
            pair.getMinutesShared().add(minutes);
            pair.addMinutes(minutes);
            pair.getMatchesSharedById().add(id);
            pairs.add(pair);
        }
    }


//    if there is already such a pair, add the shared minutes and matches to the pair in the list (avoids duplication of Pairs so that we don't end up with one pair (PlayerA, PlayerB) and another pair (PlayerB, PlayerA);
    public static void addSharedMinutesToExistingPair(List<Pair> pairs, Pair pair, Long id, int minutes) {
        for (Pair pairX : pairs) {
            if (pair.getA() == pairX.getA() && pair.getB() == pairX.getB()) {
                pairX.getMinutesShared().add(minutes);
                pairX.addMinutes(minutes);
                pairX.getMatchesSharedById().add(id);
                break;
            }
        }
    }

    public static void processPairMinutesForMatch(List<Pair> pairs, Record recordPlayerA, Record recordPlayerB, Long matchId) {
        Player playerA = recordPlayerA.getPlayer();
        Player playerB = recordPlayerB.getPlayer();

//        check if playerB has different Id and is a teammate of playerA
        if (!Objects.equals(playerB.getId(), playerA.getId()) && Objects.equals(playerB.getTeam().getName(), playerA.getTeam().getName())) {
//                        get minutes ranges for playerA and playerB
            int aStart = recordPlayerA.getFromMinutes();
            int aEnd = recordPlayerA.getToMinutes();
            int bStart = recordPlayerB.getFromMinutes();
            int bEnd = recordPlayerB.getToMinutes();

//            calculate minutes if they overlap
            if ((aStart <= bEnd) && (aEnd >= bStart)) {
                int shared = Math.min(aEnd, bEnd) - Math.max(aStart, bStart);
                Pair pair = new Pair(playerA, playerB);

//                if such a pair exists in pairs, adds the shared minutes to the pair and also checks for a duplicate pair
                SharedMinutesUtil.addSharedMinutesToExistingPair(pairs, pair, matchId, shared);
//                creates new pair and add it to the list
                SharedMinutesUtil.addNewPairToList(pairs, pair, matchId, shared);
            }
        }
    }
}
