package dev.gavhack.util.entity;

import dev.gavhack.Gavhack;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class InventoryUtil implements Wrapper {
    public static boolean isHolding(Class<? extends Item> clazz) {
        int slot = Gavhack.getInstance().getInventoryManager().getServerSlot();
        ItemStack stack = mc.thePlayer.inventory.getStackInSlot(slot);

        return clazz.isInstance(stack.getItem());
    }

    public static int getSlot(Class<? extends Item> clazz) {
        for (int i = 0; i < 9; ++i) {
            ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);
            if (clazz.isInstance(stack.getItem())) {
                return i;
            }
        }

        return -1;
    }
}
