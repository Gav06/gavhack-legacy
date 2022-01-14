package dev.gavhack.features.module.movement;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PacketEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.setting.Setting;
import dev.gavhack.util.math.VecUtil;
import dev.gavhack.util.world.BlockUtil;
import net.minecraft.src.Block;
import net.minecraft.src.BlockFluid;
import net.minecraft.src.Packet10Flying;

public class Jesus extends Module {
    public Jesus() {
        super("Jesus", Category.MOVEMENT);
    }

    public static final Setting<Boolean> dip = new Setting<>("Dip", true);
    public static final Setting<Boolean> lava = new Setting<>("Lava", true);
    public static final Setting<Integer> ticks = new Setting<>("Ticks", 4, 2, 8);

    @EventTarget
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof Packet10Flying) {
            Packet10Flying packet = (Packet10Flying) event.getPacket();

            if (isAboveLiquid() && packet.moving && mc.thePlayer.ticksExisted % ticks.getValue() == 0) {
                packet.onGround = true;
                packet.stance -= 0.5;
                packet.yPosition -= 0.5;
            }
        }
    }

    private boolean isAboveLiquid() {
        Block block = BlockUtil.getBlockFromVec(VecUtil.add(mc.thePlayer.getPosition(1.0f), 0, -2, 0));
        if (!lava.getValue() && (block == Block.lavaMoving || block == Block.lavaStill)) {
            return false;
        }

        return block instanceof BlockFluid;
    }

    private static boolean isInLiquid() {
        return mc.thePlayer.isInWater() || mc.thePlayer.handleLavaMovement();
    }

    public static boolean shouldJesus() {
        if (mc.thePlayer == null)
            return false;

        if (dip.getValue() && mc.thePlayer.fire != 0) {
            return false;
        }

        if (mc.thePlayer.fallDistance > 3.0f) {
            return false;
        }

        if (mc.gameSettings.keyBindSneak.isPressed()) {
            return false;
        }

        if (isInLiquid()) {
            return false;
        }

        return true;
    }
}
