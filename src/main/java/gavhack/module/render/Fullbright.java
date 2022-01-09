package gavhack.module.render;

import com.darkmagician6.eventapi.EventTarget;
import gavhack.event.EventLoadWorld;
import gavhack.module.Module;
import net.minecraft.src.WorldProvider;
import org.lwjgl.input.Keyboard;

public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright", Category.RENDER, Keyboard.KEY_B);
    }

    private final float[] lightmap = new float[16];

    @Override
    protected void onEnable() {
        if (mc.theWorld != null) {
            fillLightmap(mc.theWorld.provider);
        }
    }

    @Override
    protected void onDisable() {
        if (mc.theWorld != null) {
            restoreLightmap(mc.theWorld.provider);
        }
    }

    @EventTarget
    public void onWorldLoad(EventLoadWorld event) {
        fillLightmap(event.getWorld().provider);
    }

    private void fillLightmap(WorldProvider provider) {
        for (int i = 0; i < 16; i++) {
            lightmap[i] = provider.lightBrightnessTable[i];
            provider.lightBrightnessTable[i] = 1.0f;
        }
    }

    private void restoreLightmap(WorldProvider provider) {
        for (int i = 0; i < 16; i++) {
            provider.lightBrightnessTable[i] = lightmap[i];
            lightmap[i] = 0.0f;
        }
    }
}
