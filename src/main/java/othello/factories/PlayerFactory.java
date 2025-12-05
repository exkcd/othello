package othello.factories;

import othello.model.Piece;
import othello.players.HumanPlayer;
import othello.strategy.HumanPlayerStrategy;

public class PlayerFactory {
    public HumanPlayer createHumanPlayer(String name, Piece piece) {
        return new HumanPlayer(name, piece, new HumanPlayerStrategy());
    }

    // TODO: Add AI players

}
