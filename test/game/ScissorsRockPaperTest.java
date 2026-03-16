package game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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

    static Stream<Arguments> validMovesForSimulation() {
        return Stream.of(
                Arguments.of(ROCK),
                Arguments.of(PAPER),
                Arguments.of(SCISSORS)
        );
    }

    @ParameterizedTest
    @MethodSource("winningMoves")
    void shouldReturnWinWithValidParameters(ScissorsRockPaper.Move move1, ScissorsRockPaper.Move move2) {
        ScissorsRockPaper.Result result = move1.beats(move2);
        assertThat(result).isEqualTo(Result.WIN);
    }

    @ParameterizedTest
    @MethodSource("losingMoves")
    void shouldReturnLoseWithValidParameters(ScissorsRockPaper.Move move1, ScissorsRockPaper.Move move2) {
        ScissorsRockPaper.Result result = move1.beats(move2);
        assertThat(result).isEqualTo(Result.LOSE);
    }

    @ParameterizedTest
    @MethodSource("drawMoves")
    void shouldReturnDrawWithIdenticalMoves(ScissorsRockPaper.Move move1, ScissorsRockPaper.Move move2) {
        ScissorsRockPaper.Result result = move1.beats(move2);
        assertThat(result).isEqualTo(Result.DRAW);
    }

    @ParameterizedTest
    @MethodSource("validMovesForSimulation")
    void shouldReturnCorrectTotalCountWithValidParameters(ScissorsRockPaper.Move move) {
        int times = 100;
        ScissorsRockPaper.ResultCount results = game.playMultipleRounds(move, times);
        assertThat(results.total()).isEqualTo(times);
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowExceptionWithInvalidMoveParameter(ScissorsRockPaper.Move move) {
        assertThatThrownBy(
                () -> game.playMultipleRounds(move, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Moves must not be null");
    }

    @Test
    void shouldSelectBestMoveForPlayerAWithMovePlayerB() {
        List<ScissorsRockPaper.Move> playerBMoves = List.of(PAPER, PAPER, PAPER, ROCK, ROCK, SCISSORS);

        var smartPlayerA = new SmartPlayer(playerBMoves);

//        ScissorsRockPaper.Move nextMove = smartPlayerA.nextMove();

//        assertThat(nextMove).isEqualTo(SCISSORS);


        // Spieler A nimmt Schere, weil Papier am meisten gewählt wurde

    }

    private class SmartPlayer {
        private final List<ScissorsRockPaper.Move> playerBMoves;

        public SmartPlayer(List<ScissorsRockPaper.Move> playerBMoves) {
            this.playerBMoves = playerBMoves;
        }

        public ScissorsRockPaper.Move nextMove() {
            Map<ScissorsRockPaper.Move, AtomicInteger> moves = new HashMap<>();
            for (ScissorsRockPaper.Move move : playerBMoves) {
                AtomicInteger a = moves.putIfAbsent(move, new AtomicInteger(0));
                a.incrementAndGet();
            }
            return PAPER;
        }
    }
}
