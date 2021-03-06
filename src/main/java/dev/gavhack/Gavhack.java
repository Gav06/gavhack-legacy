package dev.gavhack;

import com.darkmagician6.eventapi.EventManager;
import dev.gavhack.gui.GuiHud;
import dev.gavhack.gui.clickgui.Screen;
import dev.gavhack.manager.CommandManager;
import dev.gavhack.manager.InteractionManager;
import dev.gavhack.manager.ModuleManager;
import dev.gavhack.manager.ServerManager;
import dev.gavhack.manager.config.ConfigManager;
import dev.gavhack.manager.friend.FriendManager;
import dev.gavhack.manager.inventory.InventoryManager;
import dev.gavhack.util.internal.Wrapper;

import java.util.ArrayList;

public class Gavhack {
    public static final String NAME = "Gavhack-Legacy";
    public static final String VERSION = "b3";

    private static Gavhack INSTANCE;

    private final ConfigManager configManager;
    private final ModuleManager moduleManager;
    private final CommandManager commandManager;
    private final InteractionManager interactionManager;
    private final InventoryManager inventoryManager;
    private final FriendManager friendManager;
    private final ServerManager serverManager;

    private final GuiHud hud;
    private final Screen clickGui;

    public static final ArrayList<String> recentNames = new ArrayList<>();

    private Gavhack() {
        INSTANCE = this;

        // managers
        configManager = new ConfigManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        interactionManager = new InteractionManager();
        inventoryManager = new InventoryManager();
        friendManager = new FriendManager();
        serverManager = new ServerManager();

        // gui
        hud = new GuiHud();
        clickGui = new Screen();

        EventManager.register(this);

        configManager.loadAllSafe();
        Runtime.getRuntime().addShutdownHook(new Thread(configManager::saveAllSafe));
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public InteractionManager getInteractionManager() {
        return interactionManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public FriendManager getFriendManager() {
        return friendManager;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }

    public GuiHud getHud() {
        return hud;
    }

    public Screen getClickGui() {
        return clickGui;
    }

    public static Gavhack getInstance() {
        if (INSTANCE == null) {
            new Gavhack();
        }

        return INSTANCE;
    }
}
