package othello.players;

import othello.model.Board;
import othello.model.Piece;
import othello.model.Position;
import othello.strategy.PlayerStrategy;


public class RandomAIPlayer implements Player {
    private final String name;
    private final Piece piece;
    private final PlayerStrategy strategy;

    public RandomAIPlayer(String name, Piece piece, PlayerStrategy strategy) {
        this.name = name;
        this.piece = piece;
        this.strategy = strategy;
    }

    @Override
    public Position makeMove(Board board) {
        return strategy.chooseMove(board, piece);
    }

    @Override
    public Piece getPiece() {
        return piece;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStrategyType() {
        return strategy.getStrategyName();
    }

    public PlayerStrategy getStrategy() {
        return strategy;
    }
}
