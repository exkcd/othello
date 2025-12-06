package othello.strategy;

import othello.model.Board;
import othello.model.Piece;
import othello.model.Position;

import java.util.List;
import java.util.Random;

public class RandomAIPlayerStrategy implements PlayerStrategy {
    private final Random randomMove;

    public RandomAIPlayerStrategy() {
        this.randomMove = new Random();
    }

    public RandomAIPlayerStrategy(long seed) {
        this.randomMove = new Random();
    }

    @Override
    public Position chooseMove(Board board, Piece piece) {
        List<Position> validMoves = board.getValidMoves(piece);

        if (validMoves.isEmpty()) {
            return null;
        }

        return validMoves.get(randomMove.nextInt(validMoves.size()));
    }

    @Override
    public String getStrategyName() {
        return "Random AI";
    }
}
