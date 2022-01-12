package dev.gavhack.util.math;

import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.ActiveRenderInfo;
import net.minecraft.src.GLAllocation;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.Vec3;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

public class ProjectionUtil implements Wrapper {
    private final static Matrix4f modelMatrix = new Matrix4f();
    private final static Matrix4f projectionMatrix = new Matrix4f();
    static Vec3 camPos = Vec3.createVectorHelper(0.0, 0.0, 0.0);

    public static void updateMatrix(float partialTicks) {
        if (mc.renderViewEntity == null) return;
        final Vec3 viewerPos = ActiveRenderInfo.projectViewFromEntity(mc.renderViewEntity, (double) partialTicks);
        final Vec3 relativeCamPos = Vec3.createVectorHelper(ActiveRenderInfo.objectX, ActiveRenderInfo.objectY, ActiveRenderInfo.objectZ);

        loadMatrix(modelMatrix, GL_MODELVIEW_MATRIX);
        loadMatrix(projectionMatrix, GL_PROJECTION_MATRIX);
        camPos = viewerPos.addVector(relativeCamPos.xCoord, relativeCamPos.yCoord, relativeCamPos.zCoord);
    }

    private static void loadMatrix(Matrix4f matrix, int glBit) {
        final FloatBuffer floatBuffer = GLAllocation.createDirectFloatBuffer(16);
        glGetFloat(glBit, floatBuffer);
        matrix.load(floatBuffer);
    }

    public static Vec3 toScaledScreenPos(Vec3 posIn) {
        final Vector4f vector4f = getTransformedMatrix(posIn);

        final ScaledResolution scaledResolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        final int width = scaledResolution.getScaledWidth();
        final int height = scaledResolution.getScaledHeight();

        vector4f.x = width / 2f + (0.5f * vector4f.x * width + 0.5f);
        vector4f.y = height / 2f - (0.5f * vector4f.y * height + 0.5f);
        final double posZ = isVisible(vector4f, width, height) ? 0.0 : -1.0;

        return Vec3.createVectorHelper(vector4f.x, vector4f.y, posZ);
    }

    public static Vec3 toScreenPos(Vec3 posIn) {
        final Vector4f vector4f = getTransformedMatrix(posIn);

        final int width = mc.displayWidth;
        final int height = mc.displayHeight;

        vector4f.x = width / 2f + (0.5f * vector4f.x * width + 0.5f);
        vector4f.y = height / 2f - (0.5f * vector4f.y * height + 0.5f);
        final double posZ = isVisible(vector4f, width, height) ? 0.0 : -1.0;

        return Vec3.createVectorHelper(vector4f.x, vector4f.y, posZ);
    }

    private static Vector4f getTransformedMatrix(Vec3 posIn) {
        final Vec3 relativePos = camPos.subtract(posIn);
        final Vector4f vector4f = new Vector4f((float)relativePos.xCoord, (float)relativePos.yCoord, (float)relativePos.zCoord, 1f);

        transform(vector4f, modelMatrix);
        transform(vector4f, projectionMatrix);

        if (vector4f.w > 0.0f) {
            vector4f.x *= -100000;
            vector4f.y *= -100000;
        } else {
            final float invert = 1f / vector4f.w;
            vector4f.x *= invert;
            vector4f.y *= invert;
        }

        return vector4f;
    }

    private static void transform(Vector4f vec, Matrix4f matrix) {
        final float x = vec.x;
        final float y = vec.y;
        final float z = vec.z;
        vec.x = x * matrix.m00 + y * matrix.m10 + z * matrix.m20 + matrix.m30;
        vec.y = x * matrix.m01 + y * matrix.m11 + z * matrix.m21 + matrix.m31;
        vec.z = x * matrix.m02 + y * matrix.m12 + z * matrix.m22 + matrix.m32;
        vec.w = x * matrix.m03 + y * matrix.m13 + z * matrix.m23 + matrix.m33;
    }

    private static boolean isVisible(Vector4f pos, int width, int height) {
        double right = width;
        double left = pos.x;
        if (left >= 0.0D && left <= right) {
            right = height;
            left = pos.y;
            return left >= 0.0D && left <= right;
        }
        return false;
    }
}