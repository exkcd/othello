package othello.strategy;

import othello.model.Board;
import othello.model.Piece;
import othello.model.Position;

public class HumanPlayerStrategy implements PlayerStrategy {
    private Position playerMove;

    @Override
    public Position chooseMove(Board board, Piece piece) {
        return playerMove;
    }

    public void setPlayerMove(Position move) {
        this.playerMove = move;
    }

    public void clearPlayerMove() {
        this.playerMove = null;
    }

    @Override
    public String getStrategyName() {
        return "Hooman";
    }
}
