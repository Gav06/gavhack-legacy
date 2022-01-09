package gavhack;

import gavhack.gui.GuiHud;
import gavhack.gui.Screen;
import gavhack.module.ModuleManager;
import net.minecraft.src.Minecraft;
import org.darkstorm.minecraft.gui.ExampleGuiManager;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;

public class Gavhack {

    public static final String NAME = "Gavhack-Legacy";
    public static final String VERSION = "b2";

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
    private final GuiManagerDisplayScreen clickGui;

    private Gavhack() {
        instance = this;

        this.moduleManager = new ModuleManager();
        this.hud = new GuiHud(Minecraft.getMinecraft());
        this.clickGui = new GuiManagerDisplayScreen(new ExampleGuiManager(new SimpleTheme()));
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public GuiHud getHud() {
        return hud;
    }

    public GuiManagerDisplayScreen getClickGui() {
        return clickGui;
    }
}
