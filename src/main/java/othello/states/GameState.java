package main.java.othello.states;

import main.java.othello.model.Game;

public interface GameState {

    void handleMove(Game game);

    void start(Game game);

    void pause(Game game);

    void end(Game game);

    String getStateName();

    boolean canMakeMove();

}
