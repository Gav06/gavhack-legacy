package dev.gavhack.features.command.impl;

import dev.gavhack.features.command.Command;
import dev.gavhack.features.module.Module;
import dev.gavhack.util.internal.ChatUtil;

import java.util.Arrays;
import java.util.List;

public class Toggle extends Command {
    public Toggle() {
        super(Arrays.asList("toggle", "t", "state"), "Toggles a module on or off");
    }

    @Override
    public void run(List<String> args) {
        if (args.isEmpty()) {
            ChatUtil.sendPrefixed("Please provide a command name.");
            return;
        }

        Module module = getGavhack().getModuleManager().getModule(args.get(0));
        if (module == null) {
            ChatUtil.sendPrefixed("Invalid command.");
            return;
        }

        module.toggle();

        ChatUtil.sendPrefixed("Module toggled.");
    }
}
