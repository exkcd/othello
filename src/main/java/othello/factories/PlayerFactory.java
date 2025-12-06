package othello.factories;

import othello.model.Piece;
import othello.players.*;
import othello.strategy.HumanPlayerStrategy;
import othello.strategy.RandomAIPlayerStrategy;

public class PlayerFactory {

    public HumanPlayer createHumanPlayer(String name, Piece piece) {
        return new HumanPlayer(name, piece, new HumanPlayerStrategy());
    }

    public RandomAIPlayer createRandomAIPlayer(String name, Piece piece) {
        return new RandomAIPlayer(name, piece, new RandomAIPlayerStrategy());
    }
}
