package othello.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testInitialBoard() {
        assertEquals(Board.SIZE, board.getSize());
        assertEquals(Piece.WHITE, board.getPiece(new Position(3,3)));
        assertEquals(Piece.BLACK, board.getPiece(new Position(3,4)));
        assertEquals(Piece.BLACK, board.getPiece(new Position(4,3)));
        assertEquals(Piece.WHITE, board.getPiece(new Position(4,4)));
    }

    @Test
    public void isValidMove() {
        Position
    }
}
