package dev.gavhack.event;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.ScaledResolution;

public class Render2dEvent implements Event {

    private final float partialTicks;
    private final FontRenderer font;
    private final ScaledResolution resolution;

    public Render2dEvent(float partialTicks, FontRenderer font, ScaledResolution resolution) {
        this.partialTicks = partialTicks;
        this.font = font;
        this.resolution = resolution;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public FontRenderer getFont() {
        return font;
    }

    public ScaledResolution getResolution() {
        return resolution;
    }
}
