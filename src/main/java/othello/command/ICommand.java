package othello.command;

public interface ICommand {

    // Zhu Li, do the thing!
    boolean execute();

    // Revert to previous states
    void undo();
}
