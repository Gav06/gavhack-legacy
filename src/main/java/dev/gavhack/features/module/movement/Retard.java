package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventPlayerTick;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;

public class Retard extends Module {

    public Retard() {
        super("Retard", Category.MOVEMENT);
    }

    @EventTarget
    public void onTick(EventPlayerTick event) {
        float yaw = (float) (Math.random() * 360.0f) + 1.0f;
        float pitch = ((float) (Math.random() * 180.0f) + 1.0f) - 90.0f;

        mc.thePlayer.rotationYaw = yaw;
        mc.thePlayer.rotationPitch = pitch;

        mc.thePlayer.swingItem();
    }
}
