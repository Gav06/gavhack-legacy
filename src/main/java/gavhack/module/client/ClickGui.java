package gavhack.module.client;

import gavhack.module.Module;
import org.darkstorm.minecraft.gui.ExampleGuiManager;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.input.Keyboard;

public class ClickGui extends Module {

    public ClickGui() {
        super("ClickGui", Category.CLIENT, Keyboard.KEY_RCONTROL);
    }

    @Override
    protected void onEnable() {
        mc.displayGuiScreen(gavhack.getClickGui());
        disable();
    }
}
