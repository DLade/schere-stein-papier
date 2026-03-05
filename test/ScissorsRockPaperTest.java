import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;
import static ScissorsRockPaper.Move.*;
import static ScissorsRockPaper.Result.*;

class ScissorsRockPaperTest {

    @ParameterizedTest
    @CsvSource({
            "ROCK, SCISSORS",
            "SCISSORS, PAPER",
            "PAPER, ROCK"
    })
    void shouldReturnWinWithValidParameters(ScissorsRockPaper.Move move1, ScissorsRockPaper.Move move2) {
        ScissorsRockPaper.Result result = ScissorsRockPaper.evaluateMatch(move1, move2);
        assertEquals(WIN, result);
    }

    @ParameterizedTest
    @CsvSource({
            "SCISSORS, ROCK",
            "PAPER, SCISSORS",
            "ROCK, PAPER"
    })
    void shouldReturnLoseWithValidParameters(ScissorsRockPaper.Move move1, ScissorsRockPaper.Move move2) {
        ScissorsRockPaper.Result result = ScissorsRockPaper.evaluateMatch(move1, move2);
        assertEquals(LOSE, result);
    }

    @ParameterizedTest
    @CsvSource({
            "ROCK, ROCK",
            "PAPER, PAPER",
            "SCISSORS, SCISSORS"
    })
    void shouldReturnDrawWithIdenticalMoves(ScissorsRockPaper.Move move1, ScissorsRockPaper.Move move2) {
        ScissorsRockPaper.Result result = ScissorsRockPaper.evaluateMatch(move1, move2);
        assertEquals(DRAW, result);
    }

    @Test
    void shouldThrowExceptionWithNullFirstParameter() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ScissorsRockPaper.evaluateMatch(null, ROCK)
        );
        assertEquals("Moves must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithNullSecondParameter() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ScissorsRockPaper.evaluateMatch(ROCK, null)
        );
        assertEquals("Moves must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithBothNullParameters() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ScissorsRockPaper.evaluateMatch(null, null)
        );
        assertEquals("Moves must not be null", exception.getMessage());
    }
}
