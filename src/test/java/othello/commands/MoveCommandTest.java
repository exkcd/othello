package othello.commands;

import othello.command.MoveCommand;
import othello.events.EventBus;
import othello.model.Board;
import othello.model.Piece;
import othello.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for the MoveCommand class (Command Pattern).
 */
public class MoveCommandTest {
    private Board board;
    private EventBus eventBus;

    @BeforeEach
    public void setUp() {
        board = new Board();
        eventBus = new EventBus();
    }

    @Test
    public void testCanExecuteWithValidMove() {
        Position validMove = new Position(2, 3);
        MoveCommand command = new MoveCommand(board, validMove,
                Piece.BLACK, eventBus);

        assertTrue(command.canExecute());
    }

    @Test
    public void testCanExecuteWithInvalidMove() {
        Position invalidMove = new Position(0, 0);
        MoveCommand command = new MoveCommand(board, invalidMove,
                Piece.BLACK, eventBus);

        assertFalse(command.canExecute());
    }

    @Test
    public void testExecuteValidMove() {
        Position validMove = new Position(2, 3);
        MoveCommand command = new MoveCommand(board, validMove,
                Piece.BLACK, eventBus);

        assertTrue(command.execute());
        assertEquals(Piece.BLACK, board.getPiece(validMove));
    }

    @Test
    public void testExecuteInvalidMove() {
        Position invalidMove = new Position(0, 0);
        MoveCommand command = new MoveCommand(board, invalidMove,
                Piece.BLACK, eventBus);

        assertFalse(command.execute());
        assertEquals(Piece.EMPTY, board.getPiece(invalidMove));
    }

    @Test
    public void testUndoCommand() {
        Position validMove = new Position(2, 3);
        MoveCommand command = new MoveCommand(board, validMove,
                Piece.BLACK, eventBus);

        int initialBlackCount = board.countPieces(Piece.BLACK);
        int initialWhiteCount = board.countPieces(Piece.WHITE);

        command.execute();

        // Verify move was made
        assertEquals(Piece.BLACK, board.getPiece(validMove));
        assertNotEquals(initialBlackCount, board.countPieces(Piece.BLACK));

        // Undo the move
        command.undo();

        // Verify state restored
        assertEquals(Piece.EMPTY, board.getPiece(validMove));
        assertEquals(initialBlackCount, board.countPieces(Piece.BLACK));
        assertEquals(initialWhiteCount, board.countPieces(Piece.WHITE));
    }

    @Test
    public void testGetters() {
        Position pos = new Position(2, 3);
        MoveCommand command = new MoveCommand(board, pos,
                Piece.BLACK, eventBus);

        assertEquals(pos, command.getPosition());
        assertEquals(Piece.BLACK, command.getPiece());
    }

    @Test
    public void testCommandWithoutEventBus() {
        Position validMove = new Position(2, 3);
        MoveCommand command = new MoveCommand(board, validMove,
                Piece.BLACK, null);

        // Should still work without event bus
        assertTrue(command.execute());
    }
}