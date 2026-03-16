package game;

import game.players.PaperPlayer;
import game.players.Player;
import game.players.RandomPlayer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static game.HandSign.PAPER;
import static game.HandSign.ROCK;
import static game.HandSign.SCISSORS;
import static game.MatchResult.DRAW;
import static game.MatchResult.LOSE;
import static game.MatchResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ScissorsRockPaperTest {

    private final ScissorsRockPaper game = new ScissorsRockPaper();

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

    @Test
    void shouldThrowExceptionWithNullPlayers() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.playMultipleRounds(null, new PaperPlayer(), 10))
                .withMessage("Player A or Player B cannot be null");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.playMultipleRounds(new PaperPlayer(), null, 10))
                .withMessage("Player A or Player B cannot be null");
    }

    @Test
    void shouldThrowExceptionWithInvalidRounds() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.playMultipleRounds(new PaperPlayer(), new PaperPlayer(), 0))
                .withMessage("Times must be greater than 0");
    }

    @Test
    void shouldReturnCorrectResultCountWithValidParameters() {
        Player playerA = new PaperPlayer();
        Player playerB = new PaperPlayer();

        ScissorsRockPaper.ResultCount resultCount = game.playMultipleRounds(playerA, playerB, 100);

        assertThat(resultCount.total()).isEqualTo(100);
        assertThat(resultCount.draw()).isEqualTo(100);
        assertThat(resultCount.win()).isZero();
        assertThat(resultCount.lose()).isZero();
    }

    @Test
    void shouldReturnPaperWithValidParameters() {
        PaperPlayer player = new PaperPlayer();
        assertThat(player.nextMove()).isEqualTo(PAPER);
    }

    @Test
    void shouldReturnCorrectTotalWithValidParameters() {
        ScissorsRockPaper.ResultCount resultCount = new ScissorsRockPaper.ResultCount(1, 2, 3);
        assertThat(resultCount.total()).isEqualTo(6);
    }

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
