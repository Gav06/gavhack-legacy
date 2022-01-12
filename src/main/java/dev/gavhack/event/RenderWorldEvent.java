package dev.gavhack.event;

import com.darkmagician6.eventapi.events.Event;

public class RenderWorldEvent implements Event {

    private final float partialTicks;

    public RenderWorldEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
