package gavhack;

import gavhack.gui.GuiHud;
import gavhack.gui.Screen;
import gavhack.module.ModuleManager;
import net.minecraft.src.Minecraft;

public class Gavhack {

    public static final String NAME = "Gavhack-Legacy";
    public static final String VERSION = "b1";

    private static Gavhack instance;

    public static void init() {
        if (instance == null) {
            new Gavhack();
        }
    }

    public static Gavhack getInstance() {
        return instance;
    }

    private final ModuleManager moduleManager;
    private final GuiHud hud;
    private final Screen clickGui;

    private Gavhack() {
        instance = this;

        this.moduleManager = new ModuleManager();
        this.hud = new GuiHud(Minecraft.getMinecraft());
        this.clickGui = new Screen();
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public GuiHud getHud() {
        return hud;
    }

    public Screen getClickGui() {
        return clickGui;
    }
}
