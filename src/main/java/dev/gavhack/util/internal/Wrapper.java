package dev.gavhack.util.internal;

import dev.gavhack.Gavhack;
import net.minecraft.src.Minecraft;

public interface Wrapper {
    Minecraft mc = Minecraft.getMinecraft();

    default boolean nullCheck() {
        return mc.thePlayer == null || mc.theWorld == null;
    }

    default Gavhack getGavhack() {
        return Gavhack.getInstance();
    }
}
