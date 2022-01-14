package dev.gavhack.features.module;

import com.darkmagician6.eventapi.EventManager;
import com.google.gson.JsonObject;
import dev.gavhack.manager.config.Configurable;
import dev.gavhack.setting.Bind;
import dev.gavhack.setting.Setting;
import dev.gavhack.util.internal.Wrapper;
import dev.gavhack.util.math.EnumHelper;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Module implements Wrapper, Configurable {
    private boolean enabled;

    private final String name;
    private final Category category;

    private final ArrayList<Setting<?>> settings = new ArrayList<>();
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
                        settings.add((Setting<?>) field.get(this));
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

    public Bind getBindSetting() {
        return bind;
    }

    public int getBind() {
        return bind.getValue();
    }

    public void setBind(int in) {
        bind.setValue(in);
    }

    public ArrayList<Setting<?>> getSettings() {
        return settings;
    }

    @Override
    public String getConfigName() {
        return name;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JsonObject writeToJsonObject() {
        final JsonObject object = new JsonObject();

        object.addProperty("name", name);
        object.addProperty("bind", bind.getValue());
        object.addProperty("enabled", enabled);

        for (Setting<?> setting : settings) {
            if (setting.getValue() instanceof Enum<?>) {
                object.addProperty(setting.getName(), ((Setting<Enum<?>>)setting).getValue().name());
            } else if (setting.getValue() instanceof Number) {
                object.addProperty(setting.getName(), ((Setting<Number>)setting).getValue());
            } else if (setting.getValue() instanceof Boolean) {
                object.addProperty(setting.getName(), ((Setting<Boolean>)setting).getValue());
            }
        }

        return object;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void readFromJsonObject(JsonObject jsonObject) {
        if (jsonObject.get("enabled").getAsBoolean())
            enable();

        bind.setValue(jsonObject.get("bind").getAsInt());

        for (Setting setting : settings) {
            if (!jsonObject.has(setting.getName()))
                continue;

            if (setting.getValue() instanceof Enum<?>) {
                Enum enumObj = EnumHelper.getEnumFromName(jsonObject.get(setting.getName()).getAsString(), (Class<? extends Enum>) setting.getValue().getClass());
                if (enumObj != null) {
                    setting.setValue(enumObj);
                }
            } else if (setting.getValue() instanceof Number) {
                setting.setValue(jsonObject.get(setting.getName()).getAsNumber());
            } else if (setting.getValue() instanceof Boolean) {
                setting.setValue(jsonObject.get(setting.getName()).getAsBoolean());
            }
        }
    }
}