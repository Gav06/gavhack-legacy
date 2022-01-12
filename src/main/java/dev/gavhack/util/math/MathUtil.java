package dev.gavhack.util.math;

public class MathUtil {

    public static float normalize(float value, float min, float max) {
        return (value - min) / (max - min);
    }

    public static double lerp(double then, double now, float time) {
        return then + time * (now - then);
    }
}
