package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.InputUpdateEvent;
import dev.gavhack.event.PlayerTickEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.setting.Setting;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;

public class NoSlowDown extends Module {
    private static final KeyBinding[] BINDS = new KeyBinding[] {
            mc.gameSettings.keyBindForward,
            mc.gameSettings.keyBindRight,
            mc.gameSettings.keyBindLeft,
            mc.gameSettings.keyBindBack,
            mc.gameSettings.keyBindJump
    };

    public NoSlowDown() {
        super("NoSlowDown", Category.MOVEMENT);
    }

    public static final Setting<Boolean> guiMove = new Setting<>("GuiMove", true);

    @EventTarget
    public void onPlayerTick(PlayerTickEvent event) {
        if (guiMove.getValue() && (mc.currentScreen != null
                && !(mc.currentScreen instanceof GuiChat
                || mc.currentScreen instanceof GuiScreenBook
                || mc.currentScreen instanceof GuiRepair
                || mc.currentScreen instanceof GuiEditSign))) {
//            mc.currentScreen.allowUserInput = true;
            for (KeyBinding binding : BINDS) {
                KeyBinding.setKeyBindState(binding.keyCode, Keyboard.isKeyDown(binding.keyCode));
            }
        }
    }

    @EventTarget
    public void onInputUpdate(InputUpdateEvent event) {
        event.getMovementInput().moveForward *= 5.0f;
        event.getMovementInput().moveStrafe *= 5.0f;
    }
}
