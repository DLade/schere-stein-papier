package game;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class ScissorsRockPaper {

    public enum Move {
        ROCK, PAPER, SCISSORS;

        public boolean beats(Move other) {
            return (this == ROCK && other == SCISSORS) ||
                   (this == PAPER && other == ROCK) ||
                   (this == SCISSORS && other == PAPER);
        }
    }

    public enum Result {
        WIN, LOSE, DRAW
    }

    public record ResultCount(int win, int lose, int draw) {
        public int total() {
            return win + lose + draw;
        }
    }

    private final Supplier<Move> RANDOM_STRATEGY = () -> {
        Move[] allMoves = Move.values();
        return allMoves[ThreadLocalRandom.current().nextInt(allMoves.length)];
    };

    public Result determineResult(Move movePlayerA, Move movePlayerB) {
        if (movePlayerA == null || movePlayerB == null) {
            throw new IllegalArgumentException("Moves must not be null");
        }
        if (movePlayerA == movePlayerB) {
            return Result.DRAW;
        }
        return movePlayerA.beats(movePlayerB) ? Result.WIN : Result.LOSE;
    }

    public ResultCount playMultipleRounds(Move movePlayerA, int numberOfRounds) {
        if (movePlayerA == null) {
            throw new IllegalArgumentException("Moves must not be null");
        }
        if (numberOfRounds < 1) {
            throw new IllegalArgumentException("Times must be greater than 0");
        }

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < numberOfRounds; i++) {
            Move movePlayerB = RANDOM_STRATEGY.get();
            Result result = determineResult(movePlayerA, movePlayerB);

            switch (result) {
                case WIN -> wins++;
                case LOSE -> losses++;
                case DRAW -> draws++;
            }
        }
        return new ResultCount(wins, losses, draws);
    }

    public static void main(String[] args) {
        ScissorsRockPaper game = new ScissorsRockPaper();

        ResultCount resultCount = game.playMultipleRounds(Move.PAPER, 100);

        System.out.println("Rounds played: " + resultCount.total());
        System.out.println("Player A wins: " + resultCount.win + " times");
        System.out.println("Player B wins: " + resultCount.lose + " times");
        System.out.println("Draws: " + resultCount.draw + " times");
    }
}
