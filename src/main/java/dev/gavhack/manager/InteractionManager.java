package dev.gavhack.manager;

import dev.gavhack.util.entity.LocalPlayerUtil;
import dev.gavhack.util.internal.Wrapper;
import dev.gavhack.util.math.FacingUtil;
import dev.gavhack.util.math.VecUtil;
import dev.gavhack.util.world.BlockUtil;
import net.minecraft.src.EnumFacing;
import net.minecraft.src.Vec3;

// TODO: figure out what stance does
public class InteractionManager implements Wrapper {
    public void place(Vec3 vec) {
        EnumFacing facing = BlockUtil.getFacing(vec);
        if (facing == null) {
            return;
        }

        Vec3 neighbor = BlockUtil.offsetFacing(vec, FacingUtil.getOpposite(facing));

        boolean sneak = BlockUtil.shouldSneak(neighbor);
        if (sneak) {
            LocalPlayerUtil.sneak(true);
        }

        Vec3 dirVec = FacingUtil.getDirectionVec(facing);
        Vec3 hitVec = VecUtil.add(VecUtil.add(neighbor, 0.5), dirVec.xCoord * 0.5, dirVec.yCoord * 0.5, dirVec.zCoord * 0.5);

        mc.playerController.onPlayerRightClick(
                mc.thePlayer,
                mc.theWorld,
                mc.thePlayer.getHeldItem(),
                (int) neighbor.xCoord,
                (int) neighbor.yCoord,
                (int) neighbor.zCoord,
                FacingUtil.getOppositeIndex(facing),
                hitVec);

        LocalPlayerUtil.swing();

        if (sneak) {
            LocalPlayerUtil.sneak(false);
        }
    }
}
