package dev.gavhack.features.module.combat;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventPacket;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.Packet11PlayerPosition;
import net.minecraft.src.Packet7UseEntity;

public class Criticals extends Module {
    public Criticals() {
        super("Criticals", Category.COMBAT);
    }

    @EventTarget
    public void onPacketSend(EventPacket.Send event) {
        if (event.getPacket() instanceof Packet7UseEntity) {
            Packet7UseEntity packet = (Packet7UseEntity) event.getPacket();

            if (packet.isLeftClick == 1 && mc.theWorld.getEntityByID(packet.targetEntity) != null && mc.thePlayer.onGround) {
                mc.thePlayer.sendQueue.addToSendQueue(new Packet11PlayerPosition(
                        mc.thePlayer.posX, mc.thePlayer.boundingBox.minY + 0.1, mc.thePlayer.posY, mc.thePlayer.posZ, false));
                mc.thePlayer.sendQueue.addToSendQueue(new Packet11PlayerPosition(
                        mc.thePlayer.posX, mc.thePlayer.boundingBox.minY, mc.thePlayer.posY, mc.thePlayer.posZ, false));
            }
        }
    }
}
