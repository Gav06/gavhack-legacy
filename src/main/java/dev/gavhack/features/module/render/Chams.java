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
            render(model, entity, limbSwing, limbSwingAmount, renderStance, rotationYawHead, rotationPitch, scale);
            GL11.glPushMatrix();
            GL11.glColor4f(1f, 1f, 1f, 1f);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glLineWidth(1.0f);
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
            render(model, entity, limbSwing, limbSwingAmount, renderStance, rotationYawHead, rotationPitch, scale);
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
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
