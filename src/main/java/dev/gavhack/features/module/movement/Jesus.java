package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventPacket;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;

public class Jesus extends Module {
    public Jesus() {
        super("Jesus", Category.MOVEMENT);
    }

    @EventTarget
    public void onPacketSend(EventPacket.Send event) {
        if (event.getPacket() instanceof Packet11PlayerPosition) {
            if (isAboveLiquid() && mc.thePlayer.ticksExisted % 2 == 0) {
                ((Packet11PlayerPosition) event.getPacket()).stance -= 0.5;
            }
        }
    }

    // TODO: fix this bullshit
    private boolean isAboveLiquid() {
        return false;
//        return Block.blocksList[mc.theWorld.getBlockId((int) mc.thePlayer.posX, MathHelper.floor_double(mc.thePlayer.posY), (int) mc.thePlayer.posZ)] instanceof BlockFluid;
    }
}
