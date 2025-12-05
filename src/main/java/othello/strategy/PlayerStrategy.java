package othello.strategy;

import othello.model.Board;
import othello.model.Piece;
import othello.model.Position;

public interface PlayerStrategy {
    Position chooseMove(Board board, Piece piece);

    String getStrategyName();

}
