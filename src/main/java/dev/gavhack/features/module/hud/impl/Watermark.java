package dev.gavhack.features.module.hud.impl;

import dev.gavhack.Gavhack;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.features.module.hud.IHUDComponent;

public class Watermark extends Module implements IHUDComponent {
    public Watermark() {
        super("Watermark", Category.HUD);
    }

    @Override
    public String getComponentName() {
        return getName();
    }

    @Override
    public String getDisplay() {
        return Gavhack.NAME + " " + Gavhack.VERSION;
    }
}
