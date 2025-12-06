package othello.observers;

import othello.events.EventBus;
import othello.events.EventType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {
    private EventBus eventBus;
    private TestObserver testObserver;

    @BeforeEach
    public void setUp() {
        eventBus = new EventBus();
        testObserver = new TestObserver();
    }

    @Test
    public void testObserverReceivesEvents() {
        eventBus.attach(testObserver, EventType.MOVE_MADE);
        eventBus.postMessage(EventType.MOVE_MADE, "Test move");

        assertTrue(testObserver.wasNotified());
        assertEquals("Test move", testObserver.getLastData());
    }

    @Test
    public void testMultipleObserversReceiveEvents() {
        TestObserver observer1 = new TestObserver();
        TestObserver observer2 = new TestObserver();

        eventBus.attach(observer1, EventType.GAME_STARTED);
        eventBus.attach(observer2, EventType.GAME_STARTED);

        eventBus.postMessage(EventType.GAME_STARTED, "Game started");

        assertTrue(observer1.wasNotified());
        assertTrue(observer2.wasNotified());
    }

    @Test
    public void testUnsubscribeStopsNotifications() {
        eventBus.attach(testObserver, EventType.MOVE_MADE);
        eventBus.detach(testObserver);

        eventBus.postMessage(EventType.MOVE_MADE, "Test move");

        assertFalse(testObserver.wasNotified());
    }

    @Test
    public void testObserverReceivesOnlySubscribedEvents() {
        eventBus.attach(testObserver, EventType.MOVE_MADE);

        eventBus.postMessage(EventType.GAME_OVER, "Game over");
        assertFalse(testObserver.wasNotified());

        eventBus.postMessage(EventType.MOVE_MADE, "Move made");
        assertTrue(testObserver.wasNotified());
    }

    @Test
    public void testClearAllObservers() {
        eventBus.attach(testObserver, EventType.MOVE_MADE);
        eventBus.clearAll();

        eventBus.postMessage(EventType.MOVE_MADE, "Test move");
        assertFalse(testObserver.wasNotified());
    }

    @Test
    public void testGameObserverCreation() {
        GameObserver gameObserver = new GameObserver("TestObserver");
        assertEquals("TestObserver", gameObserver.getName());

        // Should not throw exception
        gameObserver.update(EventType.MOVE_MADE);
    }

    @Test
    public void testEventBusWithNullData() {
        eventBus.attach(testObserver, EventType.TURN_CHANGED);
        eventBus.postMessage(EventType.TURN_CHANGED, null);

        assertTrue(testObserver.wasNotified());
        assertNull(testObserver.getLastData());
    }

    /**
     * Helper class for testing observer functionality.
     */
    private static class TestObserver implements IObserver {
        private boolean notified = false;
        private Object lastData;

        @Override
        public void update(Object data) {
            this.notified = true;
            this.lastData = data;
        }

        public boolean wasNotified() {
            return notified;
        }

        public Object getLastData() {
            return lastData;
        }

        public void reset() {
            notified = false;
            lastData = null;
        }
    }
}