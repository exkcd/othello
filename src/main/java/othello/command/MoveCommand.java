package othello.command;

import othello.events.EventBus;
import othello.model.Board;
import othello.model.Piece;
import othello.model.Position;

public class MoveCommand implements ICommand {
    private final Board board;
    private final Position position;
    private final Piece piece;
    private final EventBus eventBus;

    public MoveCommand(Board board, Position position, Piece piece, EventBus eventBus) {
        this.board = board;
        this.position = position;
        this.piece = piece;
        this.eventBus = eventBus;
    }


    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

    @Override
    public String getDescription() {
        return "";
    }
}
