package dev.gavhack.features.command.impl;

import dev.gavhack.features.command.Command;
import dev.gavhack.util.internal.ChatUtil;

import java.util.Arrays;
import java.util.List;

public class Ping extends Command {
    public Ping() {
        super(Arrays.asList("ping", "latency"), "Displays your latency to the server");
    }

    @Override
    public void run(List<String> args) {
        ChatUtil.sendPrefixed(getGavhack().getServerManager().getLatency(mc.thePlayer.getEntityName()) + "ms");
    }
}
