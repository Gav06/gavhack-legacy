package gavhack.module.world;

import gavhack.module.Module;
import org.lwjgl.input.Keyboard;

public class NoWeather extends Module {

    public NoWeather() {
        // TODO: remove all the stupid forced keybinds
        super("NoWeather", Category.WORLD, Keyboard.KEY_R);
    }
}
