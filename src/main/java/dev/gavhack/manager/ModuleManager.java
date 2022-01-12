package dev.gavhack.manager;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.event.EventKeyPress;
import dev.gavhack.features.module.player.MiddleClick;
import net.minecraft.src.Minecraft;

import dev.gavhack.features.module.combat.*;
import dev.gavhack.features.module.movement.*;
import dev.gavhack.features.module.render.*;
import dev.gavhack.features.module.world.*;
import dev.gavhack.features.module.client.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ModuleManager {

    private final ArrayList<Module> modules;
    private final ArrayList<Module> sortedModules;
    private final HashMap<String, Module> nameMap;
    private final HashMap<Class<? extends Module>, Module> classMap;
    private final HashMap<Category, List<Module>> categoryMap;

    public ModuleManager() {
        this.modules = new ArrayList<>();
        this.sortedModules = new ArrayList<>();
        this.nameMap = new HashMap<>();
        this.classMap = new HashMap<>();
        this.categoryMap = new HashMap<>();

        EventManager.register(this);

        register(new Fullbright());
        register(new Sprint());
        register(new AntiKnockback());
        register(new NoWeather());
        register(new ChestESP());
        register(new KillAura());
        register(new Wallhack());
        register(new Criticals());
        register(new NoFall());
        register(new Tracers());
        register(new Jesus());
        register(new AntiHunger());
        register(new Speed());
        register(new ClickGui());
        register(new Scaffold());
        register(new ForceField());
        register(new Retard());
        register(new MiddleClick());
//        register(new NewChunks());

        modules.forEach(Module::registerSettings);
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

        categoryMap.get(module.getCategory()).add(module);
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
    public void onKey(EventKeyPress event) {
        for (Module module : modules) {
            if (module.getBind() == event.getKey())
                module.toggle();
        }
    }
}