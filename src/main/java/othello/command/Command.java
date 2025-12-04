package main.java.othello.command;

public interface Command {

    // Julie, do the thing!
    void execute();

    // Revert to previous states
    void undo();

    String getDescription();
}
