package dev.gavhack.features.module.player;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PacketEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.Packet3Chat;

public class Suffix extends Module {
    private static final String SUFFIX = "\u0262\u1d00\u1d20\u029c\u1d00\u1d04\u1d0b[\u029f\u1d07\u0262\u1d00\u1d04\u028f]";

    public Suffix() {
        super("Suffix", Category.PLAYER);
    }

    @EventTarget
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof Packet3Chat) {
            String text = ((Packet3Chat) event.getPacket()).message;
            if (text.startsWith("/") || text.startsWith("&")) {
                return;
            }

            ((Packet3Chat) event.getPacket()).message += " \u23D0 " + SUFFIX;
        }
    }
}
