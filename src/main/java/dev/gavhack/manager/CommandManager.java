package dev.gavhack.manager;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.google.common.collect.Lists;
import dev.gavhack.event.PacketEvent;
import dev.gavhack.features.command.Command;
import dev.gavhack.features.command.impl.Ping;
import net.minecraft.src.Packet3Chat;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {
    private final ArrayList<Command> commands;

    private String prefix = "&";

    public CommandManager() {
        commands = Lists.newArrayList(
            new Ping()
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

        commands.stream()
                .filter((command) -> command.isTrigger(args[0]))
                .findFirst()
                .ifPresent((command) -> {
                    try {
                        command.run(Arrays.asList(args).subList(1, args.length));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
