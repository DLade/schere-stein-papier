package game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static game.ScissorsRockPaper.Move;
import static game.ScissorsRockPaper.Move.PAPER;
import static game.ScissorsRockPaper.Move.ROCK;
import static game.ScissorsRockPaper.Move.SCISSORS;
import static game.ScissorsRockPaper.Result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScissorsRockPaperTest {

    private final ScissorsRockPaper game = new ScissorsRockPaper();

    static Stream<Arguments> winningMoves() {
        return Stream.of(
                Arguments.of(ROCK, SCISSORS),
                Arguments.of(SCISSORS, PAPER),
                Arguments.of(PAPER, ROCK)
        );
    }

    static Stream<Arguments> losingMoves() {
        return Stream.of(
                Arguments.of(SCISSORS, ROCK),
                Arguments.of(PAPER, SCISSORS),
                Arguments.of(ROCK, PAPER)
        );
    }

    static Stream<Arguments> drawMoves() {
        return Stream.of(
                Arguments.of(ROCK, ROCK),
                Arguments.of(PAPER, PAPER),
                Arguments.of(SCISSORS, SCISSORS)
        );
    }

    static Stream<Arguments> invalidMoves() {
        return Stream.of(
                Arguments.of(null, ROCK),
                Arguments.of(PAPER, null),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("winningMoves")
    void shouldReturnWinWithValidParameters(Move move1, Move move2) {
        Result result = game.determineResult(move1, move2);
        assertThat(result).isEqualTo(Result.WIN);
    }

    @ParameterizedTest
    @MethodSource("losingMoves")
    void shouldReturnLoseWithValidParameters(Move move1, Move move2) {
        Result result = game.determineResult(move1, move2);
        assertThat(result).isEqualTo(Result.LOSE);
    }

    @ParameterizedTest
    @MethodSource("drawMoves")
    void shouldReturnDrawWithIdenticalMoves(Move move1, Move move2) {
        Result result = game.determineResult(move1, move2);
        assertThat(result).isEqualTo(Result.DRAW);
    }

    @ParameterizedTest
    @MethodSource("invalidMoves")
    void shouldThrowExceptionWithInvalidParameters(Move move1, Move move2) {
        assertThatThrownBy(
                () -> game.determineResult(move1, move2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Moves must not be null");
    }

    @ParameterizedTest
    @EnumSource(Move.class)
    void shouldReturnCorrectTotalCountWithValidParameters(Move move) {
        int times = 100;
        ScissorsRockPaper.ResultCount results = game.playMultipleRounds(move, times);
        assertThat(results.total()).isEqualTo(times);
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowExceptionWithInvalidParameters(Move move) {
        assertThatThrownBy(
                () -> game.playMultipleRounds(move, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Moves must not be null");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void shouldThrowExceptionWithInvalidParameters(int numberOfRounds) {
        assertThatThrownBy(
                () -> game.playMultipleRounds(PAPER, numberOfRounds))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Times must be greater than 0");
    }
}
