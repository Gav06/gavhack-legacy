package dev.gavhack.gui.setting;

import dev.gavhack.gui.Component;
import dev.gavhack.setting.Bind;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.Gui;
import org.lwjgl.input.Keyboard;

public class BindComponent extends Component implements Wrapper {

    private boolean isBinding = false;

    private final Bind bind;

    public BindComponent(Bind bind, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.bind = bind;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isInside(mouseX, mouseY) && mouseButton == 0) {
            isBinding = !isBinding;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(x, y, x + width, y + height, 0xc8909090);
        final String s = isBinding ? "Press a key..." : "Bind: " + Keyboard.getKeyName(bind.getValue());
        mc.fontRenderer.drawStringWithShadow(s, x + 2, y + (height / 2) - (mc.fontRenderer.FONT_HEIGHT / 2), (isInside(mouseX, mouseY) ? 0xc8c8c8 : -1));
    }

    @Override
    public void keyTyped(int keyCode) {
        if (isBinding) {
            if (keyCode == Keyboard.KEY_DELETE) {
                bind.setValue(Keyboard.KEY_NONE);
            } else {
                bind.setValue(keyCode);
            }
            isBinding = false;
        }
    }

    public Bind getBind() {
        return bind;
    }
}
