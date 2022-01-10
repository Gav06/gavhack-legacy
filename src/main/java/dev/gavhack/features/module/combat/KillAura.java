package dev.gavhack.features.module.combat;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventPlayerTick;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.manager.inventory.Swap;
import dev.gavhack.setting.Setting;
import dev.gavhack.util.entity.InventoryUtil;
import dev.gavhack.util.entity.LocalPlayerUtil;
import dev.gavhack.util.internal.Timer;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.ItemSword;
import org.lwjgl.input.Keyboard;

import java.util.concurrent.ThreadLocalRandom;

public class KillAura extends Module {
    public KillAura() {
        super("KillAura", Category.COMBAT);
    }

    public static final Setting<Double> range = new Setting<>("Range", 5.0, 1.0, 6.0);
    public static final Setting<Double> wallRange = new Setting<>("WallRange", 3.0, 1.0, 6.0);

    public static final Setting<Boolean> silentSwap = new Setting<>("SilentSwap", false); // dont turn to true, it wont work for some reason
    public static final Setting<Boolean> autoBlock = new Setting<>("AutoBlock", true);

    public static final Setting<Integer> delay = new Setting<>("Delay", 25, 0, 2500);

    private final Timer timer = new Timer();
    private long randomDelay = 0L;

    @EventTarget
    public void onTick(EventPlayerTick event) {
        int oldSlot = -1;
        if (silentSwap.getValue() && !InventoryUtil.isHolding(ItemSword.class)) {
            int slot = InventoryUtil.getSlot(ItemSword.class);
            if (slot != -1) {
                oldSlot = mc.thePlayer.inventory.currentItem;
                getGavhack().getInventoryManager().swap(Swap.PACKET, slot);
            }
        }

        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (!(entity instanceof EntityLivingBase) ||
                    ((EntityLivingBase) entity).getHealth() <= 0.0f ||
                    entity.isDead ||
                    entity.equals(mc.thePlayer)) {
                continue;
            }

            double dist = mc.thePlayer.getDistanceToEntity(entity);
            if ((!mc.thePlayer.canEntityBeSeen(entity) && dist > wallRange.getValue()) || dist > range.getValue()) {
                continue;
            }

            if (timer.passed(delay.getValue() + randomDelay)) {
                timer.reset();
                randomDelay = ThreadLocalRandom.current().nextLong(0, 100);

                if (autoBlock.getValue() && mc.thePlayer.isBlocking()) {
                    mc.thePlayer.stopUsingItem();
                }

                LocalPlayerUtil.attack(entity);
                LocalPlayerUtil.swing();
            } else {
                if (autoBlock.getValue() && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword) {
                    mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.getHeldItem());
                }
            }
        }

        if (oldSlot != -1) {
            getGavhack().getInventoryManager().swap(Swap.PACKET, oldSlot);
        }
    }
}
