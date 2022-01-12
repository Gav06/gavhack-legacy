package dev.gavhack.features.module.render;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventRenderWorld;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.util.math.MathUtil;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL32;

import java.awt.*;

public class Tracers extends Module {

    public Tracers() {
        super("Tracers", Category.RENDER);
    }

    @EventTarget
    public void onWorldRender(EventRenderWorld event) {
        for (EntityPlayer player : mc.theWorld.playerEntities) {
            if (player.equals(mc.thePlayer))
                continue;

            final double x = MathUtil.lerp(player.lastTickPosX, player.posX, event.getPartialTicks()) - RenderManager.renderPosX;
            final double y = MathUtil.lerp(player.lastTickPosY, player.posY, event.getPartialTicks()) - RenderManager.renderPosY;
            final double z = MathUtil.lerp(player.lastTickPosZ, player.posZ, event.getPartialTicks()) - RenderManager.renderPosZ;

            Color color = getDistanceFade((int)mc.thePlayer.getDistanceToEntity(player), 50);

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glLineWidth(1.0f);
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 1f);
            GL11.glEnable(GL32.GL_DEPTH_CLAMP);
            final Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawing(GL11.GL_LINE_STRIP);
            tessellator.addVertex(ActiveRenderInfo.objectX, ActiveRenderInfo.objectY, ActiveRenderInfo.objectZ);
            tessellator.addVertex(x, y, z);
            tessellator.addVertex(x, y + player.height, z);
            tessellator.draw();
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
}
