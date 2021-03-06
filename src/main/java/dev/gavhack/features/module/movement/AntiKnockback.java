package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PacketEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.Packet28EntityVelocity;
import net.minecraft.src.Packet60Explosion;

public class AntiKnockback extends Module {

    public AntiKnockback() {
        super("AntiKnockback", Category.COMBAT);
    }

    @EventTarget
    public void onPacketRead(PacketEvent.Receive event) {
        if (mc.thePlayer == null)
            return;

        if (event.getPacket() instanceof Packet28EntityVelocity) {
            final Packet28EntityVelocity packet = (Packet28EntityVelocity) event.getPacket();
            if (packet.entityId == mc.thePlayer.entityId) {
                event.setCancelled(true);
            }
        } else if (event.getPacket() instanceof Packet60Explosion) {
            final Packet60Explosion packet = (Packet60Explosion) event.getPacket();

            packet.playerVelocityX = 0f;
            packet.playerVelocityY = 0f;
            packet.playerVelocityZ = 0f;
        }
    }

}
