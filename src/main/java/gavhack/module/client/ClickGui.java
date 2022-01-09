package gavhack.module.client;

import gavhack.gui.Screen;
import gavhack.module.Module;
import org.lwjgl.input.Keyboard;

public class ClickGui extends Module {

    public ClickGui() {
        super("ClickGui", Category.CLIENT, Keyboard.KEY_RCONTROL);
    }

    @Override
    protected void onEnable() {
        mc.displayGuiScreen(new Screen());
    }
}
