package othello.command;

public interface ICommand {

    // Zhu Li, do the thing!
    void execute();

    // Revert to previous states
    void undo();

    String getDescription();
}
