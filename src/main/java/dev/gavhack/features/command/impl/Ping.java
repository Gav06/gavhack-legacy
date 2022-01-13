package dev.gavhack.features.command.impl;

import dev.gavhack.features.command.Command;
import dev.gavhack.util.internal.ChatUtil;
import net.minecraft.src.GuiPlayerInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Ping extends Command {
    public Ping() {
        super(Arrays.asList("ping", "latency"), "Displays your latency to the server");
    }

    @Override
    public void run(List<String> args) {
        int latency = 0;

        Optional<GuiPlayerInfo> info = mc.getNetHandler().playerInfoList.stream()
                .filter((inf) -> inf.name.equals(mc.thePlayer.getEntityName()))
                .findFirst();
        if (info.isPresent()) {
            latency = info.get().responseTime;
        }

        ChatUtil.sendPrefixed(latency + "ms");
    }
}
