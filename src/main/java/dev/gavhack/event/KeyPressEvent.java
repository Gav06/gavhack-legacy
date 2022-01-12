package dev.gavhack.event;

import com.darkmagician6.eventapi.events.Event;

public class KeyPressEvent implements Event {

    private final int key;

    public KeyPressEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
