package dev.gavhack.features.module.render;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.WorldLoadEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.WorldProvider;

public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright", Category.RENDER);
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
    public void onWorldLoad(WorldLoadEvent event) {
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
