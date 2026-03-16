package game.players;

import game.HandSign;

import java.util.Map;
import java.util.Optional;

/*
 * Find the best move based on records of player B or, if not given, return a random move.
 */
public class SmartPlayer extends RandomPlayer implements Player {
    private final Map<HandSign, Integer> recordsPlayerB;

    public SmartPlayer(Map<HandSign, Integer> recordsPlayerB) {
        if (recordsPlayerB == null) {
            throw new IllegalArgumentException("Records cannot be null");
        }
        this.recordsPlayerB = recordsPlayerB;
    }

    @Override
    public HandSign nextMove() {
        return mostFrequentHandSign(recordsPlayerB).orElse(super.nextMove());
    }

    private Optional<HandSign> mostFrequentHandSign(Map<HandSign, Integer> recordsPlayerB) {
        return recordsPlayerB
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
