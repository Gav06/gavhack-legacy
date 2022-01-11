package dev.gavhack.gui;

import dev.gavhack.Gavhack;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.GuiScreen;

import java.util.ArrayList;

public class Screen extends GuiScreen {

    private final ArrayList<Component> components;

    public Screen() {
        this.components = new ArrayList<>();

        int x = 4;
        for (Category category : Category.values()) {
            final ModulePanel panel = new ModulePanel(category.getDisplayName(), x, 4, 100, 14);
            for (Module module : Gavhack.getInstance().getModuleManager().getModulesFromCategory(category)) {
                panel.getChildren().add(new ModuleButton(module, 0, 0, panel.width, panel.height - 2));
            }

            components.add(panel);
            x += 104;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (Component component : components) {
            component.draw(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (Component component : components) {
            component.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void mouseMovedOrUp(int mouseX, int mouseY, int mouseButton) {
        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }
}
