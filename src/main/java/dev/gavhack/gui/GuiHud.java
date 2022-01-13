package dev.gavhack.gui;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.Gavhack;
import dev.gavhack.event.PacketEvent;
import dev.gavhack.event.Render2dEvent;
import dev.gavhack.features.module.Module;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.Gui;
import net.minecraft.src.Minecraft;

public class GuiHud extends Gui implements Wrapper {

    public GuiHud() {
        EventManager.register(this);
    }

    @EventTarget
    public void renderHud(Render2dEvent event) {
        if (mc.gameSettings.showDebugInfo)
            return;

        event.getFont().drawString(EnumChatFormatting.GREEN + Gavhack.NAME + " " + Gavhack.VERSION, 2, 2, -1, true);


        int y = 2;
        for (Module module : Gavhack.getInstance().getModuleManager().getSortedModules()) {
            if (module.isEnabled()) {
                event.getFont().drawString(EnumChatFormatting.GREEN + module.getName(),
                        event.getResolution().getScaledWidth() - event.getFont().getStringWidth(module.getName()) - 2,
                        y, -1, true);
                y += 10;
            }
        }

        if (!mc.isSingleplayer() && getGavhack().getServerManager().isLagging()) {
            long lagTime = getGavhack().getServerManager().getLagTime() / 1000L;

            String text = String.format("%sServer not responding for %sms", EnumChatFormatting.GREEN, lagTime);
            event.getFont().drawStringWithShadow(text, (event.getResolution().getScaledWidth() / 2) - (event.getFont().getStringWidth(text) / 2), 14, -1);
        }

//        if (!(mc.currentScreen instanceof GuiManagerDisplayScreen)) {
//            Gavhack.getInstance().getClickGui().getGuiManager().renderPinned();
//        }
    }
}
