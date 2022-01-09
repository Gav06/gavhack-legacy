package dev.gavhack;

import dev.gavhack.gui.GuiHud;
import dev.gavhack.manager.InteractionManager;
import dev.gavhack.manager.ModuleManager;
import net.minecraft.src.Minecraft;
import org.darkstorm.minecraft.gui.DarkstormGuiManager;
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
    private final InteractionManager interactionManager;
    private final GuiHud hud;
    private final GuiManagerDisplayScreen clickGui;

    private Gavhack() {
        instance = this;

        this.moduleManager = new ModuleManager();
        this.interactionManager = new InteractionManager();

        this.hud = new GuiHud(Minecraft.getMinecraft());
        this.clickGui = new GuiManagerDisplayScreen(new DarkstormGuiManager(new SimpleTheme()));
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public InteractionManager getInteractionManager() {
        return interactionManager;
    }

    public GuiHud getHud() {
        return hud;
    }

    public GuiManagerDisplayScreen getClickGui() {
        return clickGui;
    }
}
