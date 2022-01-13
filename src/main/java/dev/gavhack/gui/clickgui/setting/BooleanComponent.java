package dev.gavhack.gui.clickgui.setting;

import dev.gavhack.gui.common.Component;
import dev.gavhack.setting.Setting;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.Gui;

public class BooleanComponent extends Component implements Wrapper {

    private final Setting<Boolean> setting;

    public BooleanComponent(Setting<Boolean> setting, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setting = setting;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isInside(mouseX, mouseY) && mouseButton == 0) {
            setting.setValue(!setting.getValue());
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        final String s = setting.getName() + ": " + (setting.getValue() ? EnumChatFormatting.GREEN + "true" : EnumChatFormatting.RED + "false");
        Gui.drawRect(x, y, x + width, y + height, 0xc8909090);
        mc.fontRenderer.drawStringWithShadow(s, x + 2, y + (height / 2) - (mc.fontRenderer.FONT_HEIGHT / 2), (isInside(mouseX, mouseY) ? 0xc8c8c8 : -1));
    }

    @Override
    public void keyTyped(int keyCode) {
    }
}
