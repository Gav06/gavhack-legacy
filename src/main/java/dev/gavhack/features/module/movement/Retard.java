package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PacketEvent;
import dev.gavhack.event.PlayerTickEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.setting.Setting;
import net.minecraft.src.Packet10Flying;

public class Retard extends Module {

    private final Setting<RetardMode> mode = new Setting<>("Mode", RetardMode.Schitzo);

    public Retard() {
        super("Retard", Category.MOVEMENT);
    }

    @EventTarget
    public void onTick(PlayerTickEvent event) {
        if (mode.getValue() == RetardMode.Schitzo) {
            float yaw = (float) (Math.random() * 360.0f) + 1.0f;
            float pitch = ((float) (Math.random() * 180.0f) + 1.0f) - 90.0f;

            mc.thePlayer.rotationYaw = yaw;
            mc.thePlayer.rotationPitch = pitch;

            mc.thePlayer.swingItem();
        }
    }

    @EventTarget
    public void onPacket(PacketEvent.Send event) {
        if (mode.getValue() == RetardMode.Headless) {
            if (event.getPacket() instanceof Packet10Flying) {

                final Packet10Flying packet = (Packet10Flying) event.getPacket();

                packet.pitch = 180f;
            }
        }
    }

    public enum RetardMode {
        Schitzo,
        Headless
    }
}