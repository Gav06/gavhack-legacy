package dev.gavhack.features.module.combat;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventPlayerTick;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.Packet7UseEntity;
import org.lwjgl.input.Keyboard;


public class ForceField extends Module {

    public ForceField() {
        super("ForceField", Category.COMBAT, Keyboard.KEY_K);
    }

    @EventTarget
    public void onTick(EventPlayerTick event) {
        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (entity.equals(mc.thePlayer))
                continue;

            if (entity instanceof EntityLivingBase
                    && entity.getDistance(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ) <= 6
                    && ((EntityLivingBase)entity).getHealth() > 0) {
                mc.thePlayer.sendQueue.addToSendQueue(new Packet7UseEntity(mc.thePlayer.entityId, entity.entityId, 1));
                mc.thePlayer.swingItem();
            }
        }
    }
}
