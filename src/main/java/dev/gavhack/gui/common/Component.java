package dev.gavhack.gui.common;

public abstract class Component extends Rect implements IComponent {
    public Component(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);

    @Override
    public abstract void mouseReleased(int mouseX, int mouseY, int mouseButton);

    @Override
    public abstract void draw(int mouseX, int mouseY, float partialTicks);

    @Override
    public abstract void keyTyped(int keyCode);
}
