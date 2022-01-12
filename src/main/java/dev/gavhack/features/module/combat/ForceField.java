package dev.gavhack.features.module.combat;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PlayerTickEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.util.entity.LocalPlayerUtil;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLivingBase;

public class ForceField extends Module {

    public ForceField() {
        super("ForceField", Category.COMBAT);
    }

    @EventTarget
    public void onTick(PlayerTickEvent event) {
        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (entity instanceof EntityLivingBase) {
                if (entity == mc.thePlayer)
                    continue;

                final EntityLivingBase ent = (EntityLivingBase) entity;

                double distance = mc.thePlayer.canEntityBeSeen(ent) ? 6.0 : 2.5;

                if (ent.getHealth() > 0f && ent.getDistanceToEntity(mc.thePlayer) <= distance) {
                    LocalPlayerUtil.attack(entity);
                    LocalPlayerUtil.swing();
                }
            }
        }
    }
}
