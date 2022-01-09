package dev.gavhack.features.module.world;

import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import org.lwjgl.input.Keyboard;

public class NoWeather extends Module {

    public NoWeather() {
        // TODO: remove all the stupid forced keybinds
        super("NoWeather", Category.WORLD, Keyboard.KEY_R);
    }
}
