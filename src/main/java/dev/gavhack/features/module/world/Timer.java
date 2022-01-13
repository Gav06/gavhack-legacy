package dev.gavhack.features.module.world;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PlayerTickEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.setting.Setting;

public class Timer extends Module {
    public Timer() {
        super("Timer", Category.WORLD);
    }

    public static final Setting<Float> speed = new Setting<>("Speed", 1.5f, 0.1f, 20.0f);

    @Override
    protected void onDisable() {
        mc.timer.timerSpeed = 1.0f;
    }

    @EventTarget
    public void onTick(PlayerTickEvent event) {
        mc.timer.timerSpeed = speed.getValue();
    }
}
