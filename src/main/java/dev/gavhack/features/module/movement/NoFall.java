package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventPacket;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.Packet10Flying;
import org.lwjgl.input.Keyboard;

public class NoFall extends Module {

    public NoFall() {
        super("NoFall", Category.MOVEMENT);
    }

    @EventTarget
    public void onPacket(EventPacket.Send event) {
        if (event.getPacket() instanceof Packet10Flying) {
            if (mc.thePlayer.fallDistance > 3.0f)
                ((Packet10Flying)event.getPacket()).onGround = true;
        }
    }
}
