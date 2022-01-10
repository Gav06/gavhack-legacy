package dev.gavhack.features.module.render;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventRenderWorld;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class ChestESP extends Module {

    public ChestESP() {
        super("ChestESP", Category.RENDER);
    }

    @EventTarget
    public void onWorldRender(EventRenderWorld event) {
        for (TileEntity t : mc.theWorld.loadedTileEntityList) {
            if (t instanceof TileEntityChest || t instanceof TileEntityEnderChest) {
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glTranslated(-RenderManager.renderPosX, -RenderManager.renderPosY, -RenderManager.renderPosZ);

                if (t instanceof TileEntityChest)
                    GL11.glColor4f(0f, 1f, 0f, 1f);
                else
                    GL11.glColor4f(0.7f, 0.0f, 1.0f, 1.0f);

                GL11.glDisable(GL11.GL_LINE_SMOOTH);
                GL11.glLineWidth(1.0f);
                RenderGlobal.drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(
                        t.xCoord, t.yCoord, t.zCoord,
                        t.xCoord + 1.0, t.yCoord + 1.0, t.zCoord + 1.0));
//                GL11.glEnable(GL11.GL_LINE_SMOOTH);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glPopMatrix();
            }
        }
    }
}
