package dev.gavhack.features.module.world;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PlayerTickEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;

public class PortalChat extends Module {
    public PortalChat() {
        super("PortalChat", Category.WORLD);
    }

    @EventTarget
    public void onTick(PlayerTickEvent event) {
        mc.thePlayer.inPortal = false;
    }
}
