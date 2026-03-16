package game;

import static game.MatchResult.DRAW;
import static game.MatchResult.LOSE;
import static game.MatchResult.WIN;

enum MatchResult {
    WIN, LOSE, DRAW
}

public enum HandSign {
    ROCK, PAPER, SCISSORS;

    MatchResult beats(HandSign other) {
        if (this == other) {
            return DRAW;
        }

        return switch (this) {
            case ROCK -> (other == SCISSORS) ? WIN : LOSE;
            case PAPER -> (other == ROCK) ? WIN : LOSE;
            case SCISSORS -> (other == PAPER) ? WIN : LOSE;
        };
    }
}
