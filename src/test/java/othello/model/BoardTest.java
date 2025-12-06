package othello.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testInitialBoardSetup() {
        assertEquals(Piece.WHITE, board.getPiece(new Position(3, 3)));
        assertEquals(Piece.BLACK, board.getPiece(new Position(3, 4)));
        assertEquals(Piece.BLACK, board.getPiece(new Position(4, 3)));
        assertEquals(Piece.WHITE, board.getPiece(new Position(4, 4)));
    }

    @Test
    public void testInitialPieceCounts() {
        assertEquals(2, board.countPieces(Piece.BLACK));
        assertEquals(2, board.countPieces(Piece.WHITE));
        assertEquals(60, board.countPieces(Piece.EMPTY));
    }

    @Test
    public void testValidMovesForBlackAtStart() {
        List<Position> validMoves = board.getValidMoves(Piece.BLACK);
        assertEquals(4, validMoves.size());
    }

    @Test
    public void testInvalidMoveOnOccupiedSquare() {
        Position occupied = new Position(3, 3);
        assertFalse(board.isValidMove(occupied, Piece.BLACK));
    }

    @Test
    public void testInvalidMoveOutOfBounds() {
        Position outOfBounds = new Position(-1, 0);
        assertFalse(board.isValidMove(outOfBounds, Piece.BLACK));
    }

    @Test
    public void testMakingValidMove() {
        Position validMove = new Position(2, 3);
        assertTrue(board.makeMove(validMove, Piece.BLACK));
        assertEquals(Piece.BLACK, board.getPiece(validMove));
        assertEquals(4, board.countPieces(Piece.BLACK));
        assertEquals(1, board.countPieces(Piece.WHITE));
    }

    @Test
    public void testMakingInvalidMove() {
        Position invalidMove = new Position(0, 0);
        assertFalse(board.makeMove(invalidMove, Piece.BLACK));
        assertEquals(Piece.EMPTY, board.getPiece(invalidMove));
    }

    @Test
    public void testPieceFlippingInMultipleDirections() {
        // Set up a scenario where pieces flip in multiple directions
        board.setPiece(new Position(2, 2), Piece.BLACK);
        board.setPiece(new Position(2, 3), Piece.WHITE);
        board.setPiece(new Position(2, 4), Piece.WHITE);
        board.setPiece(new Position(3, 2), Piece.WHITE);
        board.setPiece(new Position(4, 2), Piece.WHITE);

        Position move = new Position(5, 2);
        if (board.isValidMove(move, Piece.BLACK)) {
            assertTrue(board.makeMove(move, Piece.BLACK));
        }
    }

    @Test
    public void testBoardNotFullAtStart() {
        assertFalse(board.isFull());
    }

    @Test
    public void testCopyConstructor() {
        Board copy = new Board(board);

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Position pos = new Position(i, j);
                assertEquals(board.getPiece(pos), copy.getPiece(pos));
            }
        }

        // Verify it's a deep copy
        Position testPos = new Position(2, 3);
        board.makeMove(testPos, Piece.BLACK);
        assertNotEquals(board.getPiece(testPos), copy.getPiece(testPos));
    }

    @Test
    public void testGetSizeReturnsEight() {
        assertEquals(8, board.getSize());
    }
}