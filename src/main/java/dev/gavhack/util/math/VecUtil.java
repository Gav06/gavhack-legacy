package dev.gavhack.util.math;

import net.minecraft.src.Vec3;

public class VecUtil {
    public static Vec3 add(Vec3 vec, double add) {
        return add(vec, add, add, add);
    }

    public static Vec3 add(Vec3 vec, double x, double y, double z) {
        vec.xCoord += x;
        vec.yCoord += y;
        vec.zCoord += z;

        return vec;
    }
}
