package game;

import game.players.PaperPlayer;
import game.players.Player;
import game.players.RandomPlayer;

public class ScissorsRockPaper {

    record ResultCount(int win, int lose, int draw) {
        public int total() {
            return win + lose + draw;
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
            HandSign movePlayerA = playerA.nextMove();
            HandSign movePlayerB = playerB.nextMove();
            MatchResult result = movePlayerA.beats(movePlayerB);

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
        System.out.println("Player A wins: " + resultCount.win() + " times");
        System.out.println("Player B wins: " + resultCount.lose() + " times");
        System.out.println("Draws: " + resultCount.draw() + " times");
    }
}
