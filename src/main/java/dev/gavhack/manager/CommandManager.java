package dev.gavhack.manager;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.google.common.collect.Lists;
import dev.gavhack.event.PacketEvent;
import dev.gavhack.features.command.Command;
import dev.gavhack.util.internal.ChatUtil;
import net.minecraft.src.Packet3Chat;

import dev.gavhack.features.command.impl.*;


import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {
    private final ArrayList<Command> commands;

    private String prefix = "&";

    public CommandManager() {
        commands = Lists.newArrayList(
                new Ping(),
                new Toggle(),
                new Help(),
                new BookBot()
        );

        EventManager.register(this);
    }

    @EventTarget
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof Packet3Chat) {
            Packet3Chat packet = (Packet3Chat) event.getPacket();
            if (packet.message.startsWith(prefix)) {
                event.setCancelled(true);
                handle(packet.message.substring(prefix.length()).trim().split(" "));
            }
        }
    }

    private void handle(String[] args) {
        if (args.length == 0) {
            return;
        }

        Command cmd = commands.stream()
                .filter((command) -> command.isTrigger(args[0]))
                .findFirst().orElse(null);

        if (cmd != null) {
            cmd.run(Arrays.asList(args).subList(1, args.length));
        } else {
            ChatUtil.sendPrefixed("Unable to find command, try \"" + prefix + "help\" for a list of commands");
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
