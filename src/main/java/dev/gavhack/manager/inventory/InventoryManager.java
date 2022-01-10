package dev.gavhack.manager.inventory;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventPacket;
import dev.gavhack.util.internal.Wrapper;
import dev.gavhack.util.network.NetworkUtil;
import net.minecraft.src.Packet16BlockItemSwitch;

public class InventoryManager implements Wrapper {
    private int serverSlot = -1;

    @EventTarget
    public void onPacketSend(EventPacket.Send event) {
        if (event.getPacket() instanceof Packet16BlockItemSwitch) {
            serverSlot = ((Packet16BlockItemSwitch) event.getPacket()).id;
        }
    }

    public void swap(Swap swap, int slot) {
        NetworkUtil.sendPacket(new Packet16BlockItemSwitch(slot));
        if (swap.equals(Swap.CLIENT)) {
            mc.thePlayer.inventory.currentItem = slot;
        }

        mc.playerController.updateController();
    }

    public int getServerSlot() {
        return serverSlot;
    }
}
