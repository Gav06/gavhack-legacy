package dev.gavhack.event;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.src.WorldClient;

public class WorldLoadEvent implements Event {

    private final WorldClient world;

    public WorldLoadEvent(WorldClient world) {
        this.world = world;
    }

    public WorldClient getWorld() {
        return world;
    }
}
