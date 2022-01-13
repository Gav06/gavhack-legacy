package dev.gavhack.features.module.render;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.RenderWorldEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.RenderGlobal;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class ChunkBorders extends Module {

    public ChunkBorders() {
        super("ChunkBorders", Category.RENDER);
    }

    @EventTarget
    public void onRenderWorld(RenderWorldEvent event) {
        final int chunkX = mc.thePlayer.chunkCoordX * 16;
        final int chunkZ = mc.thePlayer.chunkCoordZ * 16;

        final AxisAlignedBB chunkBB = AxisAlignedBB.getBoundingBox(
                chunkX, 0.0, chunkZ,
                chunkX + 16.0, 255.0, chunkZ + 16.0
        );

        GL11.glPushMatrix();
        GL11.glColor3f(1.0f, 1.0f, 0.0f);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glTranslated(-RenderManager.renderPosX, -RenderManager.renderPosY, -RenderManager.renderPosZ);
        GL11.glLineWidth(1f);

        RenderGlobal.drawOutlinedBoundingBox(chunkBB);

//        final Tessellator tessellator = Tessellator.instance;
//        tessellator.startDrawing(GL11.GL_LINES);
//        tessellator.addVertex(chunkX, 0.0, chunkZ);
//        tessellator.addVertex(chunkX, 255.0, chunkZ);
//        tessellator.addVertex(chunkX + 16.0, 0.0, chunkZ);
//        tessellator.addVertex(chunkX + 16.0, 255.0, chunkZ);
//        tessellator.addVertex(chunkX + 16.0, 0.0, chunkZ + 16.0);
//        tessellator.addVertex(chunkX + 16.0, 255.0, chunkZ + 16.0);
//        tessellator.addVertex(chunkX, 0.0, chunkZ + 16.0);
//        tessellator.addVertex(chunkX, 255.0, chunkZ + 16.0);
//        tessellator.draw();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
}
