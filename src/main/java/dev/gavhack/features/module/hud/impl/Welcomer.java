package dev.gavhack.features.module.hud.impl;

import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.features.module.hud.IHUDComponent;

public class Welcomer extends Module implements IHUDComponent {
    public Welcomer() {
        super("Welcomer", Category.HUD);
    }

    @Override
    public String getComponentName() {
        return getName();
    }

    @Override
    public String getDisplay() {
        return "Welcome, " + mc.getSession().getUsername() + "! :)";
    }
}
