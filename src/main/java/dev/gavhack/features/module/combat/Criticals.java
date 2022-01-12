package dev.gavhack.features.module.combat;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.AttackEntityEvent;
import dev.gavhack.event.PacketEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.setting.Setting;
import net.minecraft.src.Packet11PlayerPosition;
import net.minecraft.src.Packet7UseEntity;

public class Criticals extends Module {

    private final Setting<CritMode> critMode = new Setting<>("Mode", CritMode.Velocity);

    public Criticals() {
        super("Criticals", Category.COMBAT);
    }

    @EventTarget
    public void onPacketSend(PacketEvent.Send event) {
        if (critMode.getValue() == CritMode.Packet) {
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

    @EventTarget
    public void onAttack(AttackEntityEvent event) {
        if (critMode.getValue() == CritMode.Velocity) {
            if (mc.thePlayer.onGround && !mc.thePlayer.isSneaking() && !mc.thePlayer.isBlocking() && !mc.thePlayer.isInWater()) {
                mc.thePlayer.motionY = 0.16f;
                mc.thePlayer.fallDistance = 1.0f;
                mc.thePlayer.onGround = false;
            }
        }
    }

    public enum CritMode {
        Velocity,
        Packet
    }
}
