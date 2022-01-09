package gavhack.gui;

public interface IComponent {

    void mouseClicked(int mouseX, int mouseY, int mouseButton);

    void mouseReleased(int mouseX, int mouseY, int mouseButton);

    void drawScreen(int mouseX, int mouseY, float partialTicks);

    void keyTyped(int keyCode);
}
