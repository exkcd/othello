package othello.command;

import othello.events.EventBus;
import othello.events.EventType;
import othello.model.Board;
import othello.model.Piece;
import othello.model.Position;

public class MoveCommand implements ICommand {
    private final Board board;
    private final Position position;
    private final Piece piece;
    private final EventBus eventBus;
    private Board previousState;

    public MoveCommand(Board board, Position position, Piece piece, EventBus eventBus) {
        this.board = board;
        this.position = position;
        this.piece = piece;
        this.eventBus = eventBus;
    }


    public boolean canExecute() {
        return board.isValidMove(position, piece);
    }


    @Override
    public boolean execute() {
        if (!canExecute()) {
            if (eventBus != null) {
                eventBus.postMessage(EventType.INVALID_MOVE, "Invalid move at " + position + " by " + piece);
            }
            return false;
        }

        // State for undo
        previousState = new Board(board);

        boolean success = board.makeMove(position, piece);

        if (success && eventBus != null) {
            eventBus.postMessage(EventType.MOVE_MADE, "Move made at " + position + " by " + piece);
            eventBus.postMessage(EventType.BOARD_UPDATED, board);
        }
        return success;
    }


    @Override
    public void undo() {
        if (previousState != null) {
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    Position pos = new Position(i, j);
                    board.setPiece(pos, previousState.getPiece(pos));
                }
            }

            if (eventBus != null) {
                eventBus.postMessage(EventType.BOARD_UPDATED, board);
            }
        }
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

}
