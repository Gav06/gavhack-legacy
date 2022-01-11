package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventPacket;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.Packet10Flying;

public class Retard extends Module {

    public Retard() {
        super("Retard", Category.MOVEMENT);
    }
//
//    @EventTarget
//    public void onTick(EventPlayerTick event) {
//        float yaw =
//        float pitch =
//
//        mc.thePlayer.rotationYaw = yaw;
//        mc.thePlayer.rotationPitch = pitch;
//
//        mc.thePlayer.swingItem();
//    }
//
    @EventTarget
    public void onPacket(EventPacket.Send event) {
        if (event.getPacket() instanceof Packet10Flying) {
//            float yaw = (float) (Math.random() * 360.0f) + 1.0f;
//            float pitch = ((float) (Math.random() * 180.0f) + 1.0f) - 90.0f;

            final Packet10Flying packet = (Packet10Flying) event.getPacket();

            packet.pitch = 180f;
        }
    }
}