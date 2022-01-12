package dev.gavhack.gui.setting;

import dev.gavhack.gui.Component;
import dev.gavhack.setting.Setting;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.Gui;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberComponent extends Component implements Wrapper {

    private double sliderWidth = 0.0;
    private boolean isDragging = false;

    private final Setting<Number> setting;

    public NumberComponent(Setting<Number> setting, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setting = setting;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isInside(mouseX, mouseY) && mouseButton == 0) {
            isDragging = true;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && isDragging) {
            isDragging = false;
        }
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        updateSlider(mouseX);

        Gui.drawRect(x, y, x + width, y + height, 0xc8303030);
        Gui.drawRect(x, y, x + (int)sliderWidth, y + height, 0xc8909090);
        mc.fontRenderer.drawStringWithShadow(setting.getName() + ": " + setting.getValue().floatValue(),
                x + 2, y + (height / 2) - (mc.fontRenderer.FONT_HEIGHT / 2), (isInside(mouseX, mouseY) ? 0xc8c8c8 : -1));
    }

    protected void updateSlider(int mouseX) {
        float diff = Math.min(width, Math.max(0, mouseX - x));
        float min = setting.getMin().floatValue();
        float max = setting.getMax().floatValue();
        sliderWidth = (int) (width * (setting.getValue().floatValue() - min) / (max - min));
        if (isDragging) {
            if (diff == 0) {
                setting.setValue(setting.getMin());
            } else {
                float value = roundToPlace(diff / width * (max - min) + min, 1);
                setting.setValue(value);
            }
        }
    }

    public static float roundToPlace(float value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    @Override
    public void keyTyped(int keyCode) {
    }

    public Setting<Number> getSetting() {
        return setting;
    }
}
