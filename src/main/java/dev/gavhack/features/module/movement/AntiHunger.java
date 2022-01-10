package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.event.EventPacket;
import dev.gavhack.setting.Setting;
import net.minecraft.src.Packet11PlayerPosition;
import net.minecraft.src.Packet19EntityAction;
import org.lwjgl.input.Keyboard;

public class AntiHunger extends Module {
    public AntiHunger() {
        super("AntiHunger", Category.MOVEMENT);
    }

    public static final Setting<Boolean> ground = new Setting<>("Ground", true);
    public static final Setting<Boolean> sprint = new Setting<>("Sprint", true);

    @EventTarget
    public void onPacketSend(EventPacket.Send event) {
        if (event.getPacket() instanceof Packet11PlayerPosition) {
            if (!ground.getValue()) return;

            ((Packet11PlayerPosition) event.getPacket()).onGround = true;
        } else if (event.getPacket() instanceof Packet19EntityAction) {
            if (!sprint.getValue()) return;

            // action 4 = START_SPRINTING
            if (((Packet19EntityAction) event.getPacket()).action == 4) {
                event.setCancelled(true);
            }
        }
    }
}
