package dev.gavhack.features.module.player;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PlayerTickEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;

public class FastPlace extends Module {

    public FastPlace() {
        super("FastPlace", Category.PLAYER);
    }

    @EventTarget
    public void onTick(PlayerTickEvent event) {
        mc.rightClickDelayTimer = 0;
    }
}
