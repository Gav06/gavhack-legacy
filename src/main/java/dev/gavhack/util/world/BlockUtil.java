package dev.gavhack.util.world;

import com.google.common.collect.Lists;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.EnumFacing;
import net.minecraft.src.Vec3;

import java.util.ArrayList;

public class BlockUtil implements Wrapper {
    /**
     * Blocks we need to sneak upon placing against them
     */
    public static final ArrayList<Integer> SNEAK_BLOCKS = Lists.newArrayList(
            Block.workbench.blockID,
            Block.furnaceBurning.blockID,
            Block.furnaceIdle.blockID,
            Block.anvil.blockID,
            Block.dispenser.blockID,
            Block.dropper.blockID,
            Block.enchantmentTable.blockID,
            Block.enderChest.blockID,
            Block.chest.blockID,
            Block.chestTrapped.blockID,
            Block.bed.blockID,
            Block.beacon.blockID
    );

    public static boolean shouldSneak(Vec3 vec) {
        Block block = getBlockFromVec(vec);
        return block != null && SNEAK_BLOCKS.contains(block.blockID);
    }

    public static Block getBlockFromVec(Vec3 vec) {
        return Block.blocksList[mc.theWorld.getBlockId((int) vec.xCoord, (int) vec.yCoord, (int) vec.zCoord)];
    }

    public static Vec3 offsetFacing(Vec3 vec, EnumFacing facing) {
        vec.xCoord += facing.getFrontOffsetX();
        vec.yCoord += facing.getFrontOffsetY();
        vec.zCoord += facing.getFrontOffsetZ();

        return vec;
    }

    public static AxisAlignedBB toBoundingBox(Vec3 vec) {
        return AxisAlignedBB.getBoundingBox(vec.xCoord, vec.yCoord, vec.zCoord,
                vec.xCoord + 1, vec.yCoord + 1, vec.zCoord + 1);
    }

    public static boolean hasCollision(Vec3 vec) {
        return !mc.theWorld.getCollidingBlockBounds(toBoundingBox(vec)).isEmpty();
    }

    public static boolean canReplace(Vec3 vec) {
        Block block = getBlockFromVec(vec);
        if (block == null) {
            return true;
        }

        return block.canPlaceBlockAt(mc.theWorld, (int) vec.xCoord, (int) vec.yCoord, (int) vec.zCoord);
    }

    public static EnumFacing getFacing(Vec3 vec) {
        for (EnumFacing facing : EnumFacing.values()) {
            Vec3 neighbor = offsetFacing(mc.thePlayer.getPosition(1.0f), facing);

            if (canReplace(neighbor) && !hasCollision(vec)) {
                return facing;
            }
        }

        return null;
    }
}
