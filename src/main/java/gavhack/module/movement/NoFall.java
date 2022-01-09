package gavhack.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import gavhack.event.EventPacket;
import gavhack.module.Module;
import net.minecraft.src.Packet10Flying;
import org.lwjgl.input.Keyboard;

public class NoFall extends Module {

    public NoFall() {
        super("NoFall", Category.MOVEMENT, Keyboard.KEY_F);
    }

    @EventTarget
    public void onPacket(EventPacket.Send event) {
        if (event.getPacket() instanceof Packet10Flying) {
            if (mc.thePlayer.fallDistance > 3.0f)
                ((Packet10Flying)event.getPacket()).onGround = true;
        }
    }
}
