package game;

import java.util.concurrent.ThreadLocalRandom;

public class ScissorsRockPaper {

    enum Result {
        WIN, LOSE, DRAW
    }

    enum Move {
        ROCK {
            @Override
            Result beats(Move other) {
                if (this == other) {
                    return Result.DRAW;
                }
                return other == SCISSORS ? Result.WIN : Result.LOSE;
            }
        }, PAPER {
            @Override
            Result beats(Move other) {
                if (this == other) {
                    return Result.DRAW;
                }
                return other == ROCK ? Result.WIN : Result.LOSE;
            }
        }, SCISSORS {
            @Override
            Result beats(Move other) {
                if (this == other) {
                    return Result.DRAW;
                }
                return other == PAPER ? Result.WIN : Result.LOSE;
            }
        };

        abstract Result beats(Move other);
    }

    record ResultCount(int win, int lose, int draw) {
        public int total() {
            return win + lose + draw;
        }
    }

    interface Player {
        Move nextMove();
    }

    static class PaperPlayer implements Player {
        @Override
        public Move nextMove() {
            return Move.PAPER;
        }
    }

    static class RandomPlayer implements Player {
        @Override
        public Move nextMove() {
            Move[] allMoves = Move.values();
            return allMoves[ThreadLocalRandom.current().nextInt(allMoves.length)];
        }
    }

    ResultCount playMultipleRounds(Player playerA, Player playerB, int numberOfRounds) {
        if (playerA == null || playerB == null) {
            throw new IllegalArgumentException("Player A or Player B cannot be null");
        }
        if (numberOfRounds < 1) {
            throw new IllegalArgumentException("Times must be greater than 0");
        }

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < numberOfRounds; i++) {
            Move movePlayerA = playerA.nextMove();
            Move movePlayerB = playerB.nextMove();
            Result result = movePlayerA.beats(movePlayerB);

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

        Player playerA = new PaperPlayer();
        Player playerB = new RandomPlayer();
        ResultCount resultCount = game.playMultipleRounds(playerA, playerB, 100);

        System.out.println("Rounds played: " + resultCount.total());
        System.out.println("Player A wins: " + resultCount.win + " times");
        System.out.println("Player B wins: " + resultCount.lose + " times");
        System.out.println("Draws: " + resultCount.draw + " times");
    }
}
