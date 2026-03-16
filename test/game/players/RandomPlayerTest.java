package game.players;

import game.HandSign;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RandomPlayerTest {

    @Test
    void shouldReturnVariousMovesWithValidParameters() {
        RandomPlayer player = new RandomPlayer();
        Set<HandSign> moves = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            moves.add(player.nextMove());
        }
        assertThat(moves).containsAll(Arrays.asList(HandSign.values()));
    }
}
