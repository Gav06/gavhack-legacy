package dev.gavhack.util.network;

import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.Packet;

public class NetworkUtil implements Wrapper {
    public static void sendPacket(Packet packet) {
        mc.thePlayer.sendQueue.addToSendQueue(packet);
    }
}
