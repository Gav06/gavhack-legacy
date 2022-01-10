package dev.gavhack;

import dev.gavhack.gui.GuiHud;
import dev.gavhack.manager.InteractionManager;
import dev.gavhack.manager.ModuleManager;
import dev.gavhack.manager.friend.FriendManager;
import dev.gavhack.util.internal.Wrapper;
import org.darkstorm.minecraft.gui.DarkstormGuiManager;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;

public class Gavhack {
    public static final String NAME = "GavHack-Legacy";
    public static final String VERSION = "b2";

    private static Gavhack INSTANCE;

    private final ModuleManager moduleManager;
    private final InteractionManager interactionManager;
    private final FriendManager friendManager;

    private final GuiHud hud;
    private final GuiManagerDisplayScreen clickGui;

    private Gavhack() {
        INSTANCE = this;

        // managers
        moduleManager = new ModuleManager();
        interactionManager = new InteractionManager();
        friendManager = new FriendManager();

        // gui
        hud = new GuiHud(Wrapper.mc);
        clickGui = new GuiManagerDisplayScreen(new DarkstormGuiManager(new SimpleTheme()));
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public InteractionManager getInteractionManager() {
        return interactionManager;
    }

    public FriendManager getFriendManager() {
        return friendManager;
    }

    public GuiHud getHud() {
        return hud;
    }

    public GuiManagerDisplayScreen getClickGui() {
        return clickGui;
    }

    public static Gavhack getInstance() {
        if (INSTANCE == null) {
            new Gavhack();
        }

        return INSTANCE;
    }
}
