package dev.gavhack.util.entity;

import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.*;

public class EntityUtil implements Wrapper {
    public static boolean isInvisible(EntityLivingBase entity) {
        return entity.isPotionActive(Potion.invisibility);
    }

    public static boolean isMob(EntityLivingBase entity) {
        return entity instanceof EntityMob || entity instanceof EntitySlime;
    }

    public static boolean isPassive(EntityLivingBase entity) {
        return entity instanceof EntityAnimal || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid;
    }
}
