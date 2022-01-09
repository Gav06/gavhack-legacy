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

    public static void switchItem(int hotbarSlot) {
        NetworkUtil.sendPacket(new Packet16BlockItemSwitch(hotbarSlot));
//        mc.thePlayer.inventory.currentItem = hotbarSlot;
    }

    public static int findHotbarItem(Item item) {
        int itemSlot = -1;
        for(int i = 9; i > 0; --i) {
            if(mc.thePlayer.inventory.getStackInSlot(i).getItem().equals(item)) {
                itemSlot = i;
                break;
            }
        }
        return itemSlot;
    }
}
