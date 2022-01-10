package dev.gavhack.features.module.render;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventRenderWorld;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.ActiveRenderInfo;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Tessellator;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL32;

public class Tracers extends Module {

    public Tracers() {
        super("Tracers", Category.RENDER);
    }

    @EventTarget
    public void onWorldRender(EventRenderWorld event) {
        for (EntityPlayer player : mc.theWorld.playerEntities) {
            if (player.equals(mc.thePlayer))
                continue;

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glLineWidth(1.0f);
            GL11.glColor4f(1f, 1f, 1f, 1f);
            GL11.glEnable(GL32.GL_DEPTH_CLAMP);
            final Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawing(GL11.GL_LINES);
            tessellator.addVertex(player.posX-RenderManager.renderPosX, player.posY-RenderManager.renderPosY, player.posZ-RenderManager.renderPosZ);
            tessellator.addVertex(ActiveRenderInfo.objectX, ActiveRenderInfo.objectY, ActiveRenderInfo.objectZ);
            tessellator.draw();
            GL11.glDisable(GL32.GL_DEPTH_CLAMP);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }
    }
}
