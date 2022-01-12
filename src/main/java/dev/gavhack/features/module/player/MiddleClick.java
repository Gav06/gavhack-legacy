package dev.gavhack.features.module.player;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PlayerTickEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.MovingObjectPosition;
import org.lwjgl.input.Mouse;

public class MiddleClick extends Module {
    public MiddleClick() {
        super("MiddleClick", Category.PLAYER);
    }

    @EventTarget
    public void onTick(PlayerTickEvent event) {
        if (Mouse.isButtonDown(2) && Mouse.getEventButtonState()) {
            MovingObjectPosition result = mc.objectMouseOver;
            if (result.typeOfHit.equals(EnumMovingObjectType.ENTITY) && result.entityHit instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) result.entityHit;

                if (getGavhack().getFriendManager().isFriend(player.getUniqueID())) {
                    getGavhack().getFriendManager().remove(player);
                } else {
                    getGavhack().getFriendManager().add(player);
                }
            }
        }
    }
}
