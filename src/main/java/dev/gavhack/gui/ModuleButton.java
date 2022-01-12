package dev.gavhack.gui;

import dev.gavhack.features.module.Module;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.Gui;

import java.util.ArrayList;

public class ModuleButton extends Component implements Wrapper {

    private boolean isOpen;

    private final Module module;
    private final ArrayList<Component> children;

    public ModuleButton(Module module, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.module = module;
        this.children = new ArrayList<>();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isInside(mouseX, mouseY)) {
            if (mouseButton == 0)
                module.toggle();
            else if (mouseButton == 1)
                isOpen = !isOpen;
        }

        if (isOpen) {
            for (Component component : children) {
                component.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (isOpen) {
            for (Component component : children) {
                component.mouseReleased(mouseX, mouseY, mouseButton);
            }
        }
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        int insideColor = (isInside(mouseX, mouseY) ? 0xc8c8c8 : -1);
        Gui.drawRect(x, y, x + width, y + height, module.isEnabled() ? 0xc8404040 : 0xc8909090);
        mc.fontRenderer.drawStringWithShadow(module.getName(),
                x + 2, y + (height / 2) - (mc.fontRenderer.FONT_HEIGHT / 2), insideColor);
        if (module.getSettings().size() > 0) {
            final String s = isOpen ? "." : "...";
            mc.fontRenderer.drawStringWithShadow(s, x + width - (mc.fontRenderer.getStringWidth(s)) - 2,
                    y + (height / 2) - (mc.fontRenderer.FONT_HEIGHT / 2), insideColor);
        }

        if (isOpen) {
            int paddingY = y + height + 1;
            for (Component component : children) {
                component.x = x + 2;
                component.y = paddingY;
                component.draw(mouseX, mouseY, partialTicks);
                paddingY += component.height + 1;
            }
        }
    }

    @Override
    public void keyTyped(int keyCode) {
        if (isOpen) {
            for (Component component : children) {
                component.keyTyped(keyCode);
            }
        }
    }

    public int getAdditionalHeight() {
        if (!isOpen) {
            return 1;
        } else {
            int h = 1;
            for (Component component :children) {
                h += component.height + 1;
            }

            return h;
        }
    }

    public ArrayList<Component> getChildren() {
        return children;
    }

    public Module getModule() {
        return this.module;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
