package dev.gavhack.features.module.combat;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventAttackEntity;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.Packet11PlayerPosition;
import org.lwjgl.input.Keyboard;

public class Criticals extends Module {

    public Criticals() {
        super("Criticals", Category.COMBAT);
    }

    @EventTarget
    public void onAttack(EventAttackEntity event) {
        if (event.getAttacker() == mc.thePlayer
                && mc.thePlayer.onGround) {
            mc.thePlayer.sendQueue.addToSendQueue(new Packet11PlayerPosition(
                    mc.thePlayer.posX, mc.thePlayer.boundingBox.minY + 0.062602401692772, mc.thePlayer.posY, mc.thePlayer.posZ, false));
            mc.thePlayer.sendQueue.addToSendQueue(new Packet11PlayerPosition(
                    mc.thePlayer.posX, mc.thePlayer.boundingBox.minY + 0.0726023996066094, mc.thePlayer.posY, mc.thePlayer.posZ, false));
            mc.thePlayer.sendQueue.addToSendQueue(new Packet11PlayerPosition(
                    mc.thePlayer.posX, mc.thePlayer.boundingBox.minY, mc.thePlayer.posY, mc.thePlayer.posZ, false));
        }
    }
}
