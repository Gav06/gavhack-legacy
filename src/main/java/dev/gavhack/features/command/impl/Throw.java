package dev.gavhack.features.command.impl;

import dev.gavhack.features.command.Command;
import dev.gavhack.util.internal.ChatUtil;
import dev.gavhack.util.network.NetworkUtil;
import net.minecraft.src.ItemExpBottle;
import net.minecraft.src.Packet15Place;

import java.util.Arrays;
import java.util.List;

public class Throw extends Command {

    public Throw() {
        super(Arrays.asList("throw"), "Sends a specified number of throw packets");
    }


    @Override
    public void run(List<String> args) {
        if (args.size() > 1) {
            ChatUtil.sendPrefixed("Please specify an amount");
            return;
        }

        int amount = 0;

        try {
            amount = Integer.parseInt(args.get(0));
        } catch (NumberFormatException e) {
            ChatUtil.sendPrefixed("Invalid amount");
            return;
        }

        for (int i = 0; i < amount; i++) {
            NetworkUtil.sendPacket(new Packet15Place(-1, -1, -1, 255, mc.thePlayer.getHeldItem(), 0f, 0f, 0f));
        }
    }
}
