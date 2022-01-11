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
            components.add(new ModulePanel(category.getDisplayName(), x, 4, 100, 14));
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

    public ArrayList<Component> getComponents() {
        return components;
    }
}
