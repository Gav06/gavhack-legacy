package dev.gavhack.util.math;

import net.minecraft.src.EnumFacing;
import net.minecraft.src.Vec3;

public class FacingUtil {
    public static EnumFacing[] VALUES = EnumFacing.values();

    public static Vec3 getDirectionVec(EnumFacing facing) {
        return Vec3.createVectorHelper(
                facing.getFrontOffsetX(),
                facing.getFrontOffsetY(),
                facing.getFrontOffsetZ()
        );
    }

    public static int getOppositeIndex(EnumFacing facing) {
        int ordinal = facing.ordinal();
        return ordinal == 0 ? 1 : ordinal - 1;
    }

    public static EnumFacing getOpposite(EnumFacing facing) {
        int index = getOppositeIndex(facing);
        return VALUES[Math.abs(index % VALUES.length)];
    }
}
