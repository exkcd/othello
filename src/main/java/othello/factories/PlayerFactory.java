package main.java.othello.factories;

import ain.java.othello.model.Piece;
import main.java.othello.model.Player;

public interface PlayerFactory {

    Player createHumanPlayer(Piece piece);

    Player createEasyAIPlayer(Piece piece);

    // TODO: add more players

    Player createPlayer(String type, Piece piece);

}
