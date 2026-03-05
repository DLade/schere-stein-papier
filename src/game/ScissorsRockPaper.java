package game;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ScissorsRockPaper {
    public enum Move {
        ROCK, PAPER, SCISSORS
    }

    public enum Result {
        WIN, LOSE, DRAW
    }

    private static final Random random = ThreadLocalRandom.current();

    private static final Map<Move, Move> winAgainstMap = Map.of(
            Move.ROCK, Move.SCISSORS,
            Move.PAPER, Move.ROCK,
            Move.SCISSORS, Move.PAPER
    );

    public static Result evaluateMatch(Move move1, Move move2) {
        if (move1 == null || move2 == null) {
            throw new IllegalArgumentException("Moves must not be null");
        }
        if (move1 == move2) {
            return Result.DRAW;
        }
        if (winAgainstMap.get(move1) == move2) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private static Move randomMove() {
        final Move[] moves = Move.values();

        return moves[random.nextInt(moves.length)];
    }

    public static void main(String[] args) {
        Move movePlayerA = Move.PAPER;

        int[] resultCount = new int[Result.values().length];
        for (int i = 0; i < 100; i++) {
            Move movePlayerB = randomMove();
            Result result = evaluateMatch(movePlayerA, movePlayerB);

            resultCount[result.ordinal()] += 1;
        }

        System.out.println("Player A wins " + resultCount[Result.WIN.ordinal()] + " times");
        System.out.println("Player A loses " + resultCount[Result.LOSE.ordinal()] + " times");
        System.out.println("Player A and B draw " + resultCount[Result.DRAW.ordinal()] + " times");
    }
}
