package dev.gavhack.manager;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.Gavhack;
import dev.gavhack.event.KeyPressEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.features.module.client.ClickGui;
import dev.gavhack.features.module.client.FakePlayer;
import dev.gavhack.features.module.combat.Criticals;
import dev.gavhack.features.module.combat.ForceField;
import dev.gavhack.features.module.combat.KillAura;
import dev.gavhack.features.module.hud.impl.Welcomer;
import dev.gavhack.features.module.movement.*;
import dev.gavhack.features.module.player.FastPlace;
import dev.gavhack.features.module.player.MiddleClick;
import dev.gavhack.features.module.render.*;
import dev.gavhack.features.module.world.NoWeather;
import dev.gavhack.features.module.world.Scaffold;
import dev.gavhack.features.module.world.Timer;
import net.minecraft.src.Minecraft;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ModuleManager {

    private final ArrayList<Module> modules;
    private final ArrayList<Module> sortedModules;
    private final ArrayList<Module> hudModules;
    private final HashMap<String, Module> nameMap;
    private final HashMap<Class<? extends Module>, Module> classMap;
    private final HashMap<Category, List<Module>> categoryMap;

    public ModuleManager() {
        this.modules = new ArrayList<>();
        this.sortedModules = new ArrayList<>();
        this.hudModules = new ArrayList<>();
        this.nameMap = new HashMap<>();
        this.classMap = new HashMap<>();
        this.categoryMap = new HashMap<>();

        EventManager.register(this);

        // client modules
        register(new ClickGui());
        register(new FakePlayer());

        // combat modules
        register(new Criticals());
        register(new ForceField());
        register(new KillAura());

        // hud
        register(new Welcomer());

        // movement modules
        register(new AntiHunger());
        register(new AntiKnockback());
        register(new Jesus());
        register(new NoFall());
        register(new NoSlowDown());
        register(new Retard());
        register(new Speed());
        register(new Sprint());

        // player modules
        register(new MiddleClick());
        register(new FastPlace());

        // render modules
        register(new ChestESP());
        register(new Fullbright());
        register(new NameTags());
        register(new ChunkBorders());
        register(new PlayerESP());
        register(new Wallhack());

        // world modules
        register(new NoWeather());
        register(new Scaffold());
        register(new Timer());

        modules.sort(this::sortAlphabetical);

        for (Category category : Category.values()) {
            categoryMap.get(category).sort(this::sortAlphabetical);
        }

        sortedModules.addAll(modules);
        sortedModules.sort(Comparator.comparing(module -> -Minecraft.getMinecraft().fontRenderer.getStringWidth(module.getName())));
    }

    private void register(Module module) {
        modules.add(module);
        nameMap.put(module.getName(), module);
        classMap.put(module.getClass(), module);
        if (!categoryMap.containsKey(module.getCategory())) {
            categoryMap.put(module.getCategory(), new ArrayList<>());
        }
        module.registerSettings();
        categoryMap.get(module.getCategory()).add(module);

        if (module.getCategory().equals(Category.HUD)) {
            hudModules.add(module);
        }

        Gavhack.getInstance().getConfigManager().getConfigurables().add(module);
    }

    private int sortAlphabetical(Module module1, Module module2) {
        return module1.getName().compareTo(module2.getName());
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public ArrayList<Module> getSortedModules() {
        return sortedModules;
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(String name) {
        return (T) nameMap.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(Class<T> clazz) {
        return (T) classMap.get(clazz);
    }

    public List<Module> getModulesFromCategory(Category category) {
        return categoryMap.get(category);
    }

    @EventTarget
    public void onKey(KeyPressEvent event) {
        for (Module module : modules) {
            if (module.getBind() == event.getKey())
                module.toggle();
        }
    }
}