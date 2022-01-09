package gavhack.gui;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import gavhack.Gavhack;
import gavhack.event.EventRender2d;
import gavhack.module.Module;
import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.Gui;
import net.minecraft.src.Minecraft;
import org.lwjgl.input.Keyboard;

public class GuiHud extends Gui {

    private final Minecraft mc;

    public GuiHud(Minecraft mc) {
        this.mc = mc;
        EventManager.register(this);
    }

    @EventTarget
    public void renderHud(EventRender2d event) {
        if (mc.gameSettings.showDebugInfo)
            return;

        event.getFont().drawStringWithShadow(EnumChatFormatting.GREEN + Gavhack.NAME + " " + Gavhack.VERSION, 2, 2, -1);


        int y = 12;
        for (Module module : Gavhack.getInstance().getModuleManager().getModules()) {
            event.getFont().drawStringWithShadow(
                    (module.isEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED)
                    + module.getName() + EnumChatFormatting.RESET + "=" + Keyboard.getKeyName(module.getBind()), 2, y, -1);
//            event.getFont().drawString("poo", 10, 10, -1);
            y += 10;
        }
    }
}
