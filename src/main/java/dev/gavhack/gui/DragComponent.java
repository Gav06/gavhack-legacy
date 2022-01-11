package dev.gavhack.gui;

public class DragComponent extends Component {

    private boolean isDragging = false;
    private int dragX;
    private int dragY;

    public DragComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isInside(mouseX, mouseY) && mouseButton == 0) {
            dragX = mouseX - x;
            dragY = mouseY - y;
            isDragging = true;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && isDragging)
            isDragging = false;
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        if (isDragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
        }
    }

    @Override
    public void keyTyped(int keyCode) { }
}
