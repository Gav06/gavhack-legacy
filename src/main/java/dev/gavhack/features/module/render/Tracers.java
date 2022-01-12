package dev.gavhack.features.module.render;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.RenderWorldEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.setting.Setting;
import dev.gavhack.util.math.MathUtil;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL32;

import java.awt.*;

public class Tracers extends Module {

    private final Setting<Boolean> posts = new Setting<>("Posts", true);

    private final Setting<Float> lineWidth = new Setting<>("LineWidth", 1.0f, 1.0f, 10f);

    private final Setting<Boolean> players = new Setting<>("Players", true);
    private final Setting<Boolean> mobs = new Setting<>("Mobs", true);
    private final Setting<Boolean> animals = new Setting<>("Animals", true);
    private final Setting<Boolean> others = new Setting<>("Others", true);

    public Tracers() {
        super("Tracers", Category.RENDER);
    }

    @EventTarget
    public void onWorldRender(RenderWorldEvent event) {
        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (entity.equals(mc.thePlayer))
                continue;

            if (!shouldTracer(entity))
                continue;

            final double x = MathUtil.lerp(entity.lastTickPosX, entity.posX, event.getPartialTicks()) - RenderManager.renderPosX;
            final double y = MathUtil.lerp(entity.lastTickPosY, entity.posY, event.getPartialTicks()) - RenderManager.renderPosY;
            final double z = MathUtil.lerp(entity.lastTickPosZ, entity.posZ, event.getPartialTicks()) - RenderManager.renderPosZ;

            Color color = getDistanceFade((int)mc.thePlayer.getDistanceToEntity(entity), 50);

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glLineWidth(lineWidth.getValue());
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 1f);
            GL11.glEnable(GL32.GL_DEPTH_CLAMP);
            final Tessellator tessellator = Tessellator.instance;
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
            tessellator.startDrawing(GL11.GL_LINE_STRIP);
            tessellator.addVertex(ActiveRenderInfo.objectX, ActiveRenderInfo.objectY, ActiveRenderInfo.objectZ);
            tessellator.addVertex(x, y, z);
            if (posts.getValue())
                tessellator.addVertex(x, y + entity.height, z);

            tessellator.draw();
            GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_DONT_CARE);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
            GL11.glDisable(GL32.GL_DEPTH_CLAMP);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }
    }

    private Color getDistanceFade(int distance, int max) {
        final float normal = MathUtil.normalize(distance, 0f, max);
        return new Color(1f - normal, normal, 0f);
    }

    private boolean shouldTracer(Entity entity) {
        if (entity instanceof EntityPlayer) {
            return players.getValue();
        } else if (entity instanceof EntityMob) {
            return mobs.getValue();
        } else if (entity instanceof EntityAnimal) {
            return animals.getValue();
        } else {
            return others.getValue() && entity instanceof EntityLivingBase;
        }
    }
}
