package dev.gavhack.features.module;

import com.darkmagician6.eventapi.EventManager;
import dev.gavhack.Gavhack;
import dev.gavhack.setting.Bind;
import dev.gavhack.setting.Setting;
import dev.gavhack.util.internal.Wrapper;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Module implements Wrapper {
    private boolean enabled;

    private final String name;
    private final Category category;

    protected final Gavhack gavhack = Gavhack.getInstance();

    private final ArrayList<Setting> settings = new ArrayList<>();
    private final Bind bind = new Bind("Bind", Keyboard.KEY_NONE);

    public Module(String name, Category category, int bind) {
        this.name = name;
        this.category = category;
        this.bind.setValue(bind);
    }

    public Module(String name, Category category) {
        this(name, category, Keyboard.KEY_NONE);
    }

    public void registerSettings() {
        Arrays.stream(getClass().getDeclaredFields())
                .filter((field) -> Setting.class.isAssignableFrom(field.getType()))
                .forEach((field) -> {
                    field.setAccessible(true);

                    try {
                        settings.add((Setting) field.get(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void toggle() {
        if (enabled) disable(); else enable();
    }

    public void enable() {
        if (!enabled) {
            enabled = true;
            EventManager.register(this);
            onEnable();
        }
    }

    public void disable() {
        if (enabled) {
            enabled = false;
            EventManager.unregister(this);
            onDisable();
        }
    }

    protected void onEnable() { }

    protected void onDisable() { }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public int getBind() {
        return bind.getValue();
    }

    public void setBind(int in) {
        bind.setValue(in);
    }
}
