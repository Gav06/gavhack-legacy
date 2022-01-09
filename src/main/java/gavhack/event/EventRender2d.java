package gavhack.event;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.ScaledResolution;

public class EventRender2d implements Event {

    private final float partialTicks;
    private final FontRenderer font;
    private final ScaledResolution resolution;

    public EventRender2d(float partialTicks, FontRenderer font, ScaledResolution resolution) {
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
