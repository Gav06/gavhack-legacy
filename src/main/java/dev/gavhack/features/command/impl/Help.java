package dev.gavhack.features.command.impl;

import dev.gavhack.features.command.Command;
import dev.gavhack.util.internal.ChatUtil;

import java.util.Arrays;
import java.util.List;

public class Help extends Command {
    public Help() {
        super(Arrays.asList("?", "help", "h"), "Displays this message");
    }

    @Override
    public void run(List<String> args) {
        ChatUtil.sendPrefixed("Commands:");
        for (Command command : getGavhack().getCommandManager().getCommands()) {
            String trigs = String.join(", ", command.getTriggers());
            ChatUtil.sendPrefixed(trigs + ": " + command.getDescription());
        }
    }
}
