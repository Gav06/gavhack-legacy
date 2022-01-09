package dev.gavhack.event;

import com.darkmagician6.eventapi.events.Event;

public class EventKeyPress implements Event {

    private final int key;

    public EventKeyPress(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
