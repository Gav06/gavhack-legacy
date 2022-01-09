package dev.gavhack.gui;

import net.minecraft.src.GuiScreen;

public class Screen extends GuiScreen {

    public Screen() {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void mouseMovedOrUp(int mouseX, int mouseY, int mouseButton) {
        System.out.println(mouseX + " " + mouseY);
    }


}
