package othello.players;

import othello.model.Board;
import othello.model.Piece;
import othello.model.Position;

public interface Player {

    Position makeMove(Board board);

    Piece getPiece();

    String getName();

    String getStrategyType();

    Boolean isHuman();
}
