package game.players;

import game.HandSign;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static game.HandSign.PAPER;
import static game.HandSign.ROCK;
import static game.HandSign.SCISSORS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class SmartPlayerTest {

    static Stream<Arguments> validCombinations() {
        return Stream.of(
                Arguments.of(Map.of(SCISSORS, 1), ROCK),
                Arguments.of(Map.of(PAPER, 1), SCISSORS),
                Arguments.of(Map.of(ROCK, 1), PAPER),
                Arguments.of(Map.of(ROCK, 1, PAPER, 2, SCISSORS, 3), ROCK)
        );
    }

    @ParameterizedTest
    @MethodSource("validCombinations")
    void shouldSelectMostFrequentHandSignWithValidParameters(Map<HandSign, Integer> records, HandSign expectedMove) {
        Player player = new SmartPlayer(records);
        assertThat(player.nextMove()).isEqualTo(expectedMove);
    }

    @Test
    void shouldReturnRandomMoveWithEmptyParameters() {
        Map<HandSign, Integer> records = Map.of();
        SmartPlayer player = spy(new SmartPlayer(records));

        player.nextMove();

        verify(player).generateRandomMove();
    }
}
