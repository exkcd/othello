package othello.strategy;

import othello.model.Board;
import othello.model.Piece;
import othello.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testHumanPlayerStrategyName() {
        HumanPlayerStrategy strategy = new HumanPlayerStrategy();
        assertEquals("Hooman", strategy.getStrategyName());
    }

    @Test
    public void testHumanPlayerStrategyPendingMove() {
        HumanPlayerStrategy strategy = new HumanPlayerStrategy();
        Position move = new Position(2, 3);

        strategy.setPlayerMove(move);
        assertEquals(move, strategy.chooseMove(board, Piece.BLACK));
    }

    @Test
    public void testHumanPlayerStrategyClearPendingMove() {
        HumanPlayerStrategy strategy = new HumanPlayerStrategy();
        Position move = new Position(2, 3);

        strategy.setPlayerMove(move);
        strategy.clearPlayerMove();
        assertNull(strategy.chooseMove(board, Piece.BLACK));
    }

    @Test
    public void testRandomAIStrategyName() {
        RandomAIPlayerStrategy strategy = new RandomAIPlayerStrategy();
        assertEquals("Random AI", strategy.getStrategyName());
    }

    @Test
    public void testRandomAIStrategyChoosesValidMove() {
        RandomAIPlayerStrategy strategy = new RandomAIPlayerStrategy(42L);
        Position move = strategy.chooseMove(board, Piece.BLACK);

        assertNotNull(move);
        assertTrue(board.isValidMove(move, Piece.BLACK));
    }

    @Test
    public void testRandomAIStrategyReturnsNullWhenNoMoves() {
        // Create a scenario with no valid moves
        Board fullBoard = new Board();
        for (int i = 0; i < fullBoard.getSize(); i++) {
            for (int j = 0; j < fullBoard.getSize(); j++) {
                fullBoard.setPiece(new Position(i, j), Piece.BLACK);
            }
        }

        RandomAIPlayerStrategy strategy = new RandomAIPlayerStrategy();
        Position move = strategy.chooseMove(fullBoard, Piece.WHITE);

        assertNull(move);
    }

    @Test
    public void testRandomAIWithDifferentSeeds() {
        RandomAIPlayerStrategy strategy1 = new RandomAIPlayerStrategy(100L);
        RandomAIPlayerStrategy strategy2 = new RandomAIPlayerStrategy(200L);

        Position move1 = strategy1.chooseMove(board, Piece.BLACK);
        Position move2 = strategy2.chooseMove(board, Piece.BLACK);

        // Both should be valid, might be different
        assertNotNull(move1);
        assertNotNull(move2);
        assertTrue(board.isValidMove(move1, Piece.BLACK));
        assertTrue(board.isValidMove(move2, Piece.BLACK));
    }
}