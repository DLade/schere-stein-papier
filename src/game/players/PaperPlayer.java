package game.players;

import game.HandSign;

public class PaperPlayer implements Player {
    @Override
    public HandSign nextMove() {
        return HandSign.PAPER;
    }
}
