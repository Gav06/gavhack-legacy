package gavhack.module;

import com.darkmagician6.eventapi.EventManager;
import gavhack.Gavhack;
import net.minecraft.src.Minecraft;
import org.lwjgl.input.Keyboard;

public abstract class Module {

    private int bind;
    private boolean enabled;

    private final String name;
    private final Category category;

    protected final Minecraft mc = Minecraft.getMinecraft();
    protected final Gavhack gavhack = Gavhack.getInstance();

    public Module(String name, Category category, int bind) {
        this.name = name;
        this.category = category;
        this.bind = bind;
    }

    public Module(String name, Category category) {
        this(name, category, Keyboard.KEY_NONE);
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
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        RENDER("Render"),
        WORLD("World"),
        CLIENT("Client");

        private final String displayName;

        Category(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
