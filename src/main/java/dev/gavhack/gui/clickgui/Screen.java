package dev.gavhack.gui.clickgui;

import dev.gavhack.Gavhack;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.gui.common.Component;
import dev.gavhack.gui.clickgui.setting.BindComponent;
import dev.gavhack.gui.clickgui.setting.BooleanComponent;
import dev.gavhack.gui.clickgui.setting.EnumComponent;
import dev.gavhack.gui.clickgui.setting.NumberComponent;
import dev.gavhack.setting.Setting;
import net.minecraft.src.GuiScreen;

import java.util.ArrayList;

public class Screen extends GuiScreen {

    private final ArrayList<Component> components;

    @SuppressWarnings("unchecked")
    public Screen() {
        this.components = new ArrayList<>();

        int x = 4;
        for (Category category : Category.values()) {
            final ModulePanel panel = new ModulePanel(category.getDisplayName(), x, 4, 100, 14);
            for (Module module : Gavhack.getInstance().getModuleManager().getModulesFromCategory(category)) {
                final ModuleButton button = new ModuleButton(module, 0, 0, panel.width, panel.height - 2);

                for (Setting<?> setting : module.getSettings()) {
                    if (setting.getValue() instanceof Boolean) {
                        button.getChildren().add(new BooleanComponent((Setting<Boolean>) setting, 0, 0, button.width - 2, button.height));
                    } else if (setting.getValue() instanceof Number) {
                        button.getChildren().add(new NumberComponent((Setting<Number>) setting, 0, 0, button.width - 2, button.height));
                    } else if (setting.getValue() instanceof Enum) {
                        button.getChildren().add(new EnumComponent((Setting<Enum<?>>) setting, 0, 0, button.width - 2, button.height));
                    }
                }

                button.getChildren().add(new BindComponent(module.getBindSetting(), 0, 0, button.width - 2, button.height));

                panel.getChildren().add(button);
            }

            components.add(panel);
            x += 106;
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
    public void keyTyped(char keyChar, int keyCode) {
        super.keyTyped(keyChar, keyCode);

        for (Component component : components) {
            component.keyTyped(keyCode);
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
