package game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static game.HandSign.PAPER;
import static game.HandSign.ROCK;
import static game.HandSign.SCISSORS;
import static game.MatchResult.DRAW;
import static game.MatchResult.LOSE;
import static game.MatchResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

class HandSignTest {

    static Stream<Arguments> validCombinations() {
        return Stream.of(
                Arguments.of(ROCK, ROCK, DRAW),
                Arguments.of(PAPER, PAPER, DRAW),
                Arguments.of(SCISSORS, SCISSORS, DRAW),
                Arguments.of(ROCK, SCISSORS, WIN),
                Arguments.of(SCISSORS, PAPER, WIN),
                Arguments.of(PAPER, ROCK, WIN),
                Arguments.of(SCISSORS, ROCK, LOSE),
                Arguments.of(PAPER, SCISSORS, LOSE),
                Arguments.of(ROCK, PAPER, LOSE)
        );
    }

    @ParameterizedTest
    @MethodSource("validCombinations")
    void shouldReturnCorrectResultWithValidParameters(HandSign move1, HandSign move2, MatchResult expectedResult) {
        MatchResult result = move1.beats(move2);

        assertThat(result).isEqualTo(expectedResult);
    }
}
