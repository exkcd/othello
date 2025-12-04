package main.java.othello.observers;

public interface GameObserver {

    void update(GameEvents events, String message);
}
