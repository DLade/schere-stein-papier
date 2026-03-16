package game.players;

import game.HandSign;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FixedPlayerTest {

    @ParameterizedTest
    @MethodSource("allHandSigns")
    void shouldReturnCorrectHandSignWithValidParameters(HandSign handSign) {
        FixedPlayer player = new FixedPlayer(handSign);
        assertThat(player.nextMove()).isEqualTo(handSign);
    }

    @Test
    void shouldThrowExceptionWithNullParameters() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new FixedPlayer(null))
                .withMessage("HandSign cannot be null");
    }

    private static Stream<Arguments> allHandSigns() {
        return Stream.of(
                Arguments.of(HandSign.ROCK),
                Arguments.of(HandSign.PAPER),
                Arguments.of(HandSign.SCISSORS)
        );
    }
}
