package othello.observers;

import othello.events.EventType;

public interface IObservable {
    void attach(IObserver observer);

    void detach(IObserver observer);
}
