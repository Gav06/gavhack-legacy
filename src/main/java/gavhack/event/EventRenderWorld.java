package gavhack.event;

import com.darkmagician6.eventapi.events.Event;

public class EventRenderWorld implements Event {

    private final float partialTicks;

    public EventRenderWorld(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
