package dev.gavhack.gui;

import dev.gavhack.features.module.Module;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.Gui;

public class ModuleButton extends Component implements Wrapper {

    private final Module module;
    public ModuleButton(Module module, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.module = module;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isInside(mouseX, mouseY) && mouseButton == 0) {
            module.toggle();
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(x, y, x + width, y + height, 0xc8909090);
        mc.fontRenderer.drawStringWithShadow(module.getName(),
                x + 2, y + (height / 2) - (mc.fontRenderer.FONT_HEIGHT / 2),
                module.isEnabled() ? 0x00ff00 : 0xff0000);
    }

    @Override
    public void keyTyped(int keyCode) {

    }

    public Module getModule() {
        return this.module;
    }
}
