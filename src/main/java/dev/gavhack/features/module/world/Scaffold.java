package dev.gavhack.features.module.world;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PlayerTickEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.setting.Setting;
import dev.gavhack.util.entity.LocalPlayerUtil;
import dev.gavhack.util.world.BlockUtil;
import net.minecraft.src.EnumFacing;
import net.minecraft.src.Vec3;

public class Scaffold extends Module {
    public Scaffold() {
        super("Scaffold", Category.WORLD);
    }

    public static final Setting<Boolean> stopSprint = new Setting<>("StopSprint", true);
    public static final Setting<Boolean> tower = new Setting<>("Tower", true);

    @EventTarget
    public void onTick(PlayerTickEvent event) {
        Vec3 below = Vec3.createVectorHelper(mc.thePlayer.posX, Math.floor(mc.thePlayer.posY) - 1, mc.thePlayer.posZ);

        if (mc.theWorld.isAirBlock((int) below.xCoord, (int) below.yCoord, (int) below.zCoord)) {
            EnumFacing facing = BlockUtil.getFacing(below);
            if (facing == null) {
                return;
            }

            if (stopSprint.getValue() && mc.thePlayer.isSprinting()) {
                LocalPlayerUtil.sprint(false);
            }

            getGavhack().getInteractionManager().place(below);

            if (tower.getValue() && mc.gameSettings.keyBindJump.isPressed()) {
                mc.thePlayer.motionX *= 0.2;
                mc.thePlayer.motionZ *= 0.2;
                mc.thePlayer.setJumping(true);
            } else {
                mc.thePlayer.setJumping(false);
            }
        }
    }
}
