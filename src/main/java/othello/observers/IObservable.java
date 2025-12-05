package othello.observers;

import othello.events.EventType;

public interface IObservable {
    void attach(IObserver observer, EventType eventType);

    void detach(IObserver observer);
}
