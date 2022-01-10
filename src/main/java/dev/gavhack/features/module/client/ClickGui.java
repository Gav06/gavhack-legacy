package dev.gavhack.features.module.client;

import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import org.lwjgl.input.Keyboard;

public class ClickGui extends Module {

    public ClickGui() {
        super("ClickGui", Category.CLIENT, Keyboard.KEY_RCONTROL);
    }

    @Override
    protected void onEnable() {
        mc.displayGuiScreen(getGavhack().getClickGui());
        disable();
    }
}
