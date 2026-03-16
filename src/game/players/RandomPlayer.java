package game.players;

import game.HandSign;

import java.util.concurrent.ThreadLocalRandom;

public class RandomPlayer implements Player {
    @Override
    public HandSign nextMove() {
        HandSign[] allMoves = HandSign.values();
        return allMoves[ThreadLocalRandom.current().nextInt(allMoves.length)];
    }
}
