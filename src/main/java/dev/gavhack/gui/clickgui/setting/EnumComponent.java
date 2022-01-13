package dev.gavhack.gui.clickgui.setting;

import dev.gavhack.gui.common.Component;
import dev.gavhack.setting.Setting;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.Gui;

public class EnumComponent extends Component implements Wrapper {

    private int index = 0;

    private final Enum<?>[] constants;
    private final Setting<Enum<?>> setting;

    public EnumComponent(Setting<Enum<?>> setting, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setting = setting;
        this.constants = setting.getValue().getDeclaringClass().getEnumConstants();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isInside(mouseX, mouseY)) {
            if (mouseButton == 0) {
                increase();
            } else if (mouseButton == 1) {
                decrease();
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(x, y, x + width, y + height, 0xc8909090);
        mc.fontRenderer.drawStringWithShadow(setting.getName() + ": " + constants[index].name(),
                x + 2, y + (height / 2) - (mc.fontRenderer.FONT_HEIGHT / 2), (isInside(mouseX, mouseY) ? 0xc8c8c8 : -1));
    }

    @Override
    public void keyTyped(int keyCode) {

    }

    public Setting<? extends Enum<?>> getSetting() {
        return setting;
    }

    private void increase() {
        if (index == constants.length - 1) {
            index = 0;
        } else {
            index++;
        }

        setting.setValue(constants[index]);
    }

    private void decrease() {
        if (index == 0) {
            index = constants.length - 1;
        } else {
            index--;
        }

        setting.setValue(constants[index]);
    }
}
