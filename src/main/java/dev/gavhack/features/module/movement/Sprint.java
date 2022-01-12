package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PlayerTickEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @EventTarget
    public void onTick(PlayerTickEvent event) {
        if (mc.thePlayer.isSprinting())
            return;

        if (mc.thePlayer.moveForward > 0.0f
                && mc.thePlayer.getFoodStats().getFoodLevel() > 6
                && !mc.thePlayer.isSneaking()
                && !mc.thePlayer.isCollidedHorizontally) {
            mc.thePlayer.setSprinting(true);
        }
    }
}
