package dev.gavhack.gui;

import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.Gui;

import java.util.ArrayList;

public class ModulePanel extends Component implements Wrapper {

    private String title;
    private boolean isOpen;

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
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        header.mouseReleased(mouseX, mouseY, mouseButton);
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        header.draw(mouseX, mouseY, partialTicks);

        x = header.x;
        y = header.y;

        Gui.drawRect(x, y, x + width, y + height, 0xc8707070);
        mc.fontRenderer.drawStringWithShadow(title,
                x + (width / 2) - (mc.fontRenderer.getStringWidth(title) / 2),
                y - (height / 2) + (mc.fontRenderer.FONT_HEIGHT), -1);
    }

    @Override
    public void keyTyped(int keyCode) {

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
