package othello.players;

import othello.model.Board;
import othello.model.Piece;
import othello.model.Position;
import othello.strategy.HumanPlayerStrategy;
import othello.strategy.RandomAIPlayerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for Player implementations.
 */
public class PlayerTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testHumanPlayerCreation() {
        HumanPlayerStrategy strategy = new HumanPlayerStrategy();
        HumanPlayer player = new HumanPlayer("Alice", Piece.BLACK, strategy);

        assertEquals("Alice", player.getName());
        assertEquals(Piece.BLACK, player.getPiece());
        assertEquals("Hooman", player.getStrategyType());
    }

    @Test
    public void testRandomAIPlayerCreation() {
        RandomAIPlayerStrategy strategy = new RandomAIPlayerStrategy();
        RandomAIPlayer player = new RandomAIPlayer("Bot", Piece.WHITE, strategy);

        assertEquals("Bot", player.getName());
        assertEquals(Piece.WHITE, player.getPiece());
        assertEquals("Random AI", player.getStrategyType());
    }

    @Test
    public void testHumanPlayerMakeMove() {
        HumanPlayerStrategy strategy = new HumanPlayerStrategy();
        HumanPlayer player = new HumanPlayer("Alice", Piece.BLACK, strategy);

        Position expectedMove = new Position(2, 3);
        strategy.setPlayerMove(expectedMove);

        Position actualMove = player.makeMove(board);
        assertEquals(expectedMove, actualMove);
    }

    @Test
    public void testRandomAIPlayerMakesValidMove() {
        RandomAIPlayerStrategy strategy = new RandomAIPlayerStrategy(123L);
        RandomAIPlayer player = new RandomAIPlayer("Bot", Piece.BLACK, strategy);

        Position move = player.makeMove(board);

        assertNotNull(move);
        assertTrue(board.isValidMove(move, Piece.BLACK));
    }

    @Test
    public void testPlayerPolymorphism() {
        Player humanPlayer = new HumanPlayer("Human", Piece.BLACK,
                new HumanPlayerStrategy());
        Player aiPlayer = new RandomAIPlayer("AI", Piece.WHITE,
                new RandomAIPlayerStrategy());

        // Both can be treated as Player interface
        assertNotNull(humanPlayer.getName());
        assertNotNull(aiPlayer.getName());
        assertNotNull(humanPlayer.getPiece());
        assertNotNull(aiPlayer.getPiece());
    }

    @Test
    public void testPlayerStrategyInjection() {
        // Test that different strategies can be injected
        RandomAIPlayerStrategy aiStrategy = new RandomAIPlayerStrategy();
        HumanPlayerStrategy humanStrategy = new HumanPlayerStrategy();

        HumanPlayer player1 = new HumanPlayer("P1", Piece.BLACK, humanStrategy);
        RandomAIPlayer player2 = new RandomAIPlayer("P2", Piece.WHITE, aiStrategy);

        assertEquals(humanStrategy, player1.getStrategy());
        assertEquals(aiStrategy, player2.getStrategy());
    }

    @Test
    public void testPlayersWithDifferentPieces() {
        HumanPlayer blackPlayer = new HumanPlayer("Black", Piece.BLACK,
                new HumanPlayerStrategy());
        HumanPlayer whitePlayer = new HumanPlayer("White", Piece.WHITE,
                new HumanPlayerStrategy());

        assertEquals(Piece.BLACK, blackPlayer.getPiece());
        assertEquals(Piece.WHITE, whitePlayer.getPiece());
        assertNotEquals(blackPlayer.getPiece(), whitePlayer.getPiece());
    }
}