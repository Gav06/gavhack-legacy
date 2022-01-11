package dev.gavhack.gui;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventRender2d;
import dev.gavhack.features.module.Module;
import dev.gavhack.Gavhack;
import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.Gui;
import net.minecraft.src.Minecraft;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;

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

        event.getFont().drawString(EnumChatFormatting.GREEN + Gavhack.NAME + " " + Gavhack.VERSION, 2, 2, -1);


        int y = 2;
        for (Module module : Gavhack.getInstance().getModuleManager().getSortedModules()) {
            if (module.isEnabled()) {
                event.getFont().drawString(EnumChatFormatting.GREEN + module.getName(),
                        event.getResolution().getScaledWidth() - event.getFont().getStringWidth(module.getName()) - 2,
                        y, -1);
                y += 10;
            }
        }

//        if (!(mc.currentScreen instanceof GuiManagerDisplayScreen)) {
//            Gavhack.getInstance().getClickGui().getGuiManager().renderPinned();
//        }
    }
}
