package game;

import game.players.FixedPlayer;
import game.players.Player;
import org.junit.jupiter.api.Test;

import static game.HandSign.PAPER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ScissorsRockPaperTest {

    private final ScissorsRockPaper game = new ScissorsRockPaper();

    @Test
    void shouldThrowExceptionWithNullPlayers() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.playMultipleRounds(null, new FixedPlayer(PAPER), 10))
                .withMessage("Player A or Player B cannot be null");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.playMultipleRounds(new FixedPlayer(PAPER), null, 10))
                .withMessage("Player A or Player B cannot be null");
    }

    @Test
    void shouldThrowExceptionWithInvalidRounds() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> game.playMultipleRounds(new FixedPlayer(PAPER), new FixedPlayer(PAPER), 0))
                .withMessage("Times must be greater than 0");
    }

    @Test
    void shouldReturnCorrectResultCountWithValidParameters() {
        Player playerA = new FixedPlayer(PAPER);
        Player playerB = new FixedPlayer(PAPER);

        ScissorsRockPaper.ResultCount resultCount = game.playMultipleRounds(playerA, playerB, 100);

        assertThat(resultCount.total()).isEqualTo(100);
        assertThat(resultCount.draw()).isEqualTo(100);
        assertThat(resultCount.win()).isZero();
        assertThat(resultCount.lose()).isZero();
    }

    @Test
    void shouldReturnCorrectTotalWithValidParameters() {
        ScissorsRockPaper.ResultCount resultCount = new ScissorsRockPaper.ResultCount(1, 2, 3);
        assertThat(resultCount.total()).isEqualTo(6);
    }
}