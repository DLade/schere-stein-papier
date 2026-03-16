package game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static game.ScissorsRockPaper.Move;
import static game.ScissorsRockPaper.Move.PAPER;
import static game.ScissorsRockPaper.Move.ROCK;
import static game.ScissorsRockPaper.Move.SCISSORS;
import static game.ScissorsRockPaper.Result;
import static game.ScissorsRockPaper.Result.DRAW;
import static game.ScissorsRockPaper.Result.LOSE;
import static game.ScissorsRockPaper.Result.WIN;
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
    void shouldReturnCorrectResultWithValidParameters(Move move1, Move move2, Result expectedResult) {
        Result result = move1.beats(move2);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldThrowExceptionWithNullPlayers() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.playMultipleRounds(null, new ScissorsRockPaper.PaperPlayer(), 10))
                .withMessage("Player A or Player B cannot be null");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.playMultipleRounds(new ScissorsRockPaper.PaperPlayer(), null, 10))
                .withMessage("Player A or Player B cannot be null");
    }

    @Test
    void shouldThrowExceptionWithInvalidRounds() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.playMultipleRounds(new ScissorsRockPaper.PaperPlayer(), new ScissorsRockPaper.PaperPlayer(), 0))
                .withMessage("Times must be greater than 0");
    }

    @Test
    void shouldReturnCorrectResultCountWithValidParameters() {
        ScissorsRockPaper.Player playerA = new ScissorsRockPaper.PaperPlayer();
        ScissorsRockPaper.Player playerB = new ScissorsRockPaper.PaperPlayer();

        ScissorsRockPaper.ResultCount resultCount = game.playMultipleRounds(playerA, playerB, 100);

        assertThat(resultCount.total()).isEqualTo(100);
        assertThat(resultCount.draw()).isEqualTo(100);
        assertThat(resultCount.win()).isZero();
        assertThat(resultCount.lose()).isZero();
    }

    @Test
    void shouldReturnPaperWithValidParameters() {
        ScissorsRockPaper.PaperPlayer player = new ScissorsRockPaper.PaperPlayer();
        assertThat(player.nextMove()).isEqualTo(PAPER);
    }

    @Test
    void shouldReturnCorrectTotalWithValidParameters() {
        ScissorsRockPaper.ResultCount resultCount = new ScissorsRockPaper.ResultCount(1, 2, 3);
        assertThat(resultCount.total()).isEqualTo(6);
    }
}
