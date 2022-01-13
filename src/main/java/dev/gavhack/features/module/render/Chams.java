package dev.gavhack.features.module.render;

import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.ModelBase;
import org.lwjgl.opengl.GL11;

public class Chams extends Module {

    private static Chams chams;

    public Chams() {
        super("Chams", Category.RENDER);
        chams = this;
    }

    public static void renderModelHook(
            ModelBase model,
            EntityLivingBase entity,
            float limbSwing,
            float limbSwingAmount,
            float renderStance,
            float rotationYawHead,
            float rotationPitch,
            float scale) {
        if (chams.isEnabled()) {
            GL11.glDepthRange(0.0, 0.01);
            render(model, entity, limbSwing, limbSwingAmount, renderStance, rotationYawHead, rotationPitch, scale);
            GL11.glDepthRange(0.0, 1.0);
        } else{
            render(model, entity, limbSwing, limbSwingAmount, renderStance, rotationYawHead, rotationPitch, scale);
        }
    }

    private static void render(ModelBase model,
                               EntityLivingBase entity,
                               float limbSwing,
                               float limbSwingAmount,
                               float renderStance,
                               float rotationYawHead,
                               float rotationPitch,
                               float scale) {
        model.render(entity, limbSwing, limbSwingAmount, renderStance, rotationYawHead, rotationPitch, scale);
    }
}
