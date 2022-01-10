package dev.gavhack.util.entity;

import dev.gavhack.util.internal.Wrapper;
import dev.gavhack.util.network.NetworkUtil;
import net.minecraft.src.*;

public class LocalPlayerUtil implements Wrapper {
    public static void swing() {
        mc.thePlayer.swingItem();
    }

    public static void sprint(boolean sprint) {
        int action = sprint ? 4 : 5;
        NetworkUtil.sendPacket(new Packet19EntityAction(mc.thePlayer, action));
    }

    public static void sneak(boolean state) {
        int action = state ? 1 : 2;
        NetworkUtil.sendPacket(new Packet19EntityAction(mc.thePlayer, action));
    }

    public static void attack(Entity entity) {
        NetworkUtil.sendPacket(new Packet7UseEntity(mc.thePlayer.entityId, entity.entityId, 1));
    }
}
