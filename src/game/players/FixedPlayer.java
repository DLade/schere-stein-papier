package game.players;

import game.HandSign;

public class FixedPlayer implements Player {
    private final HandSign handSign;

    public FixedPlayer(HandSign handSign) {
        if (handSign == null) {
            throw new IllegalArgumentException("HandSign cannot be null");
        }
        this.handSign = handSign;
    }

    @Override
    public HandSign nextMove() {
        return handSign;
    }
}
