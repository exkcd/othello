package othello.states;

import othello.model.Board;

public class Game implements GameState {

    private Board board;

    @Override
    public void handleMove(Game game) {

    }

    @Override
    public void start(Game game) {

    }

    @Override
    public void pause(Game game) {

    }

    @Override
    public void end(Game game) {

    }

    @Override
    public String getStateName() {
        return "";
    }

    @Override
    public boolean canMakeMove() {
        return false;
    }
}
