package gavhack.event;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.src.WorldClient;

public class EventLoadWorld implements Event {

    private final WorldClient world;

    public EventLoadWorld(WorldClient world) {
        this.world = world;
    }

    public WorldClient getWorld() {
        return world;
    }
}
