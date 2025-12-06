package othello.observers;

public class GameObserver implements IObserver {
    private final String name;

    public GameObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Object data) {
        System.out.println("[" + name + "] Events: " + data);
    }

    public String getName() {
        return name;
    }
}
