package main.java.othello.strategy;

import main.java.othello.model.Board;
import main.java.othello.model.Piece;
import main.java.othello.model.Position;

public interface PlayerStrategy {

    // Choose the next move for a player
    Position chooseMove(Board board, Piece piece);

    String getStrategyName();

}
