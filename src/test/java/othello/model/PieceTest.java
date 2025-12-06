package othello.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for the Piece enum.
 */
public class PieceTest {

    @Test
    public void testPieceSymbols() {
        assertEquals('B', Piece.BLACK.getSymbol());
        assertEquals('W', Piece.WHITE.getSymbol());
        assertEquals(' ', Piece.EMPTY.getSymbol());
    }

    @Test
    public void testOppositeForBlack() {
        assertEquals(Piece.WHITE, Piece.BLACK.opponent());
    }

    @Test
    public void testOppositeForWhite() {
        assertEquals(Piece.BLACK, Piece.WHITE.opponent());
    }

    @Test
    public void testOppositeForEmpty() {
        assertEquals(Piece.EMPTY, Piece.EMPTY.opponent());
    }

    @Test
    public void testToString() {
        assertEquals("B", Piece.BLACK.toString());
        assertEquals("W", Piece.WHITE.toString());
        assertEquals(" ", Piece.EMPTY.toString());
    }

    @Test
    public void testEnumValues() {
        Piece[] pieces = Piece.values();
        assertEquals(3, pieces.length);
        assertEquals(Piece.BLACK, pieces[0]);
        assertEquals(Piece.WHITE, pieces[1]);
        assertEquals(Piece.EMPTY, pieces[2]);
    }
}