package gavhack.module;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import gavhack.event.EventKeyPress;
import gavhack.module.combat.*;
import gavhack.module.movement.*;
import gavhack.module.render.*;
import gavhack.module.world.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager {

    private final ArrayList<Module> modules;
    private final HashMap<String, Module> nameMap;
    private final HashMap<Class<? extends Module>, Module> classMap;

    public ModuleManager() {
        this.modules = new ArrayList<>();
        this.nameMap = new HashMap<>();
        this.classMap = new HashMap<>();

        EventManager.register(this);

        register(new Fullbright());
        register(new Sprint());
        register(new AntiKnockback());
        register(new NoWeather());
        register(new ChestESP());
        register(new ForceField());
        register(new Wallhack());
        register(new Criticals());
        register(new NoFall());
        register(new Tracers());
        register(new Jesus());
        register(new AntiHunger());

        modules.forEach(Module::registerSettings);
    }

    private void register(Module module) {
        modules.add(module);
        nameMap.put(module.getName(), module);
        classMap.put(module.getClass(), module);
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(String name) {
        return (T) nameMap.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(Class<T> clazz) {
        return (T) classMap.get(clazz);
    }

    @EventTarget
    public void onKey(EventKeyPress event) {
        for (Module module : modules) {
            if (module.getBind() == event.getKey())
                module.toggle();
        }
    }
}