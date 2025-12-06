package othello.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for the Position class.
 */
public class PositionTest {

    @Test
    public void testPositionCreation() {
        Position pos = new Position(3, 4);
        assertEquals(3, pos.getRow());
        assertEquals(4, pos.getCol());
    }

    @Test
    public void testIsValidWithinBounds() {
        Position pos = new Position(4, 5);
        assertTrue(pos.isValid(8));
    }

    @Test
    public void testIsValidOutOfBounds() {
        Position pos1 = new Position(-1, 3);
        Position pos2 = new Position(3, 8);
        Position pos3 = new Position(8, 3);

        assertFalse(pos1.isValid(8));
        assertFalse(pos2.isValid(8));
        assertFalse(pos3.isValid(8));
    }

    @Test
    public void testOffsetMethod() {
        Position original = new Position(3, 3);
        Position offset = original.offset(1, -1);

        assertEquals(4, offset.getRow());
        assertEquals(2, offset.getCol());

        // Verify original unchanged (immutability)
        assertEquals(3, original.getRow());
        assertEquals(3, original.getCol());
    }

    @Test
    public void testToString() {
        Position pos = new Position(5, 7);
        assertEquals("(5,7)", pos.toString());
    }

    @Test
    public void testEqualityWithNull() {
        Position pos = new Position(1, 1);
        assertNotEquals(null, pos);
    }

    @Test
    public void testEqualityWithDifferentClass() {
        Position pos = new Position(1, 1);
        String notAPosition = "1,1";
        assertNotEquals(pos, notAPosition);
    }
}