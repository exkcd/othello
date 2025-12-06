package othello.events;

import othello.observers.IObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private static EventBus instance;

    private final Map<EventType, List<IObserver>> observers = new HashMap<>();

    public EventBus() {
        for (EventType eventType : EventType.values()) {
            observers.put(eventType, new ArrayList<>());
        }
    }

    public static EventBus getInstance() {
        // Eager evaluation
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public void attach (IObserver observer, EventType eventType) {
        if (eventType == EventType.ALL) {
            for (EventType type : EventType.values()) {
                if (type != EventType.ALL) {
                    observers.get(type).add(observer);
                }
            }
        } else {
            observers.get(eventType).add(observer);
        }
    }

    public void detach (IObserver observer) {
        for (List<IObserver> observerList : observers.values()) {
            observerList.remove(observer);
        }
    }

    public void postMessage (EventType eventType, Object data) {
        List<IObserver> observerList = observers.get(eventType);
        for (IObserver observer : observerList) {
            observer.update(data);
        }
    }

    public void clearAll() {
        for (List<IObserver> observerList : observers.values()) {
            observerList.clear();
        }
    }
}
