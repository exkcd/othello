package othello.factories;

import othello.model.Piece;
import othello.players.HumanPlayer;
import othello.players.Player;
import othello.players.RandomAIPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for Factory Pattern implementations.
 */
public class FactoryTest {

    @Test
    public void testFactoryCreatesPlayers() {
        PlayerFactory factory = new PlayerFactory();
        Player hooman = factory.createHumanPlayer("Test Player", Piece.BLACK);
        Player bot = factory.createRandomAIPlayer("AI Player", Piece.WHITE);

        assertNotNull(hooman);
        assertEquals("Test Player", hooman.getName());
        assertEquals(Piece.BLACK, hooman.getPiece());


        assertNotNull(bot);
        assertEquals("AI Player", bot.getName());
        assertEquals(Piece.WHITE, bot.getPiece());
    }

    @Test
    public void testFactoriesCreateDifferentInstances() {
        PlayerFactory factory = new PlayerFactory();

        Player human = factory.createHumanPlayer("Human", Piece.BLACK);
        Player ai = factory.createRandomAIPlayer("AI", Piece.WHITE);

        assertNotEquals(human.getClass(), ai.getClass());
        assertNotEquals(human.getStrategyType(), ai.getStrategyType());
    }
}