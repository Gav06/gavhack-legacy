package gavhack.module.combat;

import com.darkmagician6.eventapi.EventTarget;
import gavhack.event.EventPlayerTick;
import gavhack.module.Module;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLivingBase;
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
                mc.playerController.attackEntity(mc.thePlayer, entity);
                mc.thePlayer.swingItem();
            }
        }
    }
}
