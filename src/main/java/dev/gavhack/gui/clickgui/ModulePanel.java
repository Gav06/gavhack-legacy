package dev.gavhack.gui.clickgui;

import dev.gavhack.gui.common.Component;
import dev.gavhack.gui.common.DragComponent;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.Gui;

import java.util.ArrayList;

public class ModulePanel extends Component implements Wrapper {

    public static final int MAX_HEIGHT = 250;

    private String title;
    private boolean isOpen = true;

    private final ArrayList<ModuleButton> children;
    private final DragComponent header;

    public ModulePanel(String title, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.title = title;
        this.children = new ArrayList<>();
        this.header = new DragComponent(x, y, width, height);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        header.mouseClicked(mouseX, mouseY, mouseButton);

        if (isOpen) {
            for (ModuleButton button : children) {
                button.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }

        if (header.isInside(mouseX, mouseY) && mouseButton == 1)
            isOpen = !isOpen;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        header.mouseReleased(mouseX, mouseY, mouseButton);

        if (isOpen) {
            for (ModuleButton button : children) {
                button.mouseReleased(mouseX, mouseY, mouseButton);
            }
        }
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        header.draw(mouseX, mouseY, partialTicks);

        x = header.x;
        y = header.y;

        if (isOpen) {
            int newHeight = header.height;
            for (ModuleButton button : children) {
                newHeight += button.height + button.getAdditionalHeight();
            }

            height = newHeight;
        } else {
            height = header.height;
        }

        Gui.drawRect(x - 1, y - 1, x + width + 1, y + height + (isOpen ? 2 : 1), 0x60909090);

        Gui.drawRect(x, y, x + width, y + header.height, 0xc8909090);
        mc.fontRenderer.drawStringWithShadow(title,
                x + (width / 2) - (mc.fontRenderer.getStringWidth(title) / 2),
                y - (header.height / 2) + (mc.fontRenderer.FONT_HEIGHT), -1);

        if (isOpen) {
            int paddingY = y + header.height + 2;
            for (ModuleButton button : children) {
                button.x = x;
                button.y = paddingY;
                button.draw(mouseX, mouseY, partialTicks);
                paddingY += button.height + button.getAdditionalHeight();
            }
        }
    }

    @Override
    public void keyTyped(int keyCode) {
        for (ModuleButton button : children ) {
            button.keyTyped(keyCode);
        }
    }

    public ArrayList<ModuleButton> getChildren() {
        return children;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
