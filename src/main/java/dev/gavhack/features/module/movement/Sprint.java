package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventPlayerTick;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import org.lwjgl.input.Keyboard;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Category.MOVEMENT, Keyboard.KEY_M);
    }

    @EventTarget
    public void onTick(EventPlayerTick event) {
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