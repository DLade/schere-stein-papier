package game.players;

import game.HandSign;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static game.HandSign.PAPER;
import static game.HandSign.ROCK;
import static game.HandSign.SCISSORS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SmartPlayerTest {

    @Test
    void shouldSelectMostFrequentHandSignWithValidParameters() {
        Map<HandSign, Integer> records = Map.of(ROCK, 1, PAPER, 3, SCISSORS, 0);

        Player player = new SmartPlayer(records);
        assertThat(player.nextMove()).isEqualTo(PAPER);
    }

    @Test
    void shouldReturnRandomMoveWithEmptyParameters() {
        Map<HandSign, Integer> records = Map.of();

        Player player = new SmartPlayer(records);
        assertThat(player.nextMove()).isNotNull();
    }

    @Test
    void shouldSelectFirstMaxHandSignWithEqualFrequencies() {
        // Use a map that guarantees order, or just Map.of and expect one of the max
        // Here we use a map with deterministic iteration if possible
        Map<HandSign, Integer> records = new java.util.LinkedHashMap<>();
        records.put(ROCK, 2);
        records.put(PAPER, 2);
        records.put(SCISSORS, 1);

        Player player = new SmartPlayer(records);
        // ROCK and PAPER have 2. ROCK is first in LinkedHashMap.
        assertThat(player.nextMove()).isEqualTo(ROCK);
    }

    @Test
    void shouldThrowExceptionWithInvalidParameters() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SmartPlayer(null))
                .withMessage("Records cannot be null");
    }
}
