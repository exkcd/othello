package othello.factories;

import othello.model.Piece;
import othello.players.HumanPlayer;
import othello.players.Player;
import othello.players.RandomAIPlayer;
import othello.strategy.HumanPlayerStrategy;
import othello.strategy.RandomAIPlayerStrategy;

public class PlayerFactory {

    public Player createHumanPlayer(String name, Piece piece) {
        return new HumanPlayer(name, piece, new HumanPlayerStrategy());
    }

    public Player createRandomAIPlayer(String name, Piece piece) {
        return new RandomAIPlayer(name, piece, new RandomAIPlayerStrategy());
    }
}
