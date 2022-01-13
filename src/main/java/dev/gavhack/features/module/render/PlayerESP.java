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

public class PlayerESP extends Module {

    private final Setting<Boolean> tracers = new Setting<>("Tracers", true);
    private final Setting<Boolean> boxes = new Setting<>("Boxes", true);

    private final Setting<Float> lineWidth = new Setting<>("LineWidth", 1.0f, 1.0f, 10f);

    public PlayerESP() {
        super("PlayerESP", Category.RENDER);
    }

    @EventTarget
    public void onWorldRender(RenderWorldEvent event) {
        for (EntityPlayer player : mc.theWorld.playerEntities) {
            doEsp(player, event.getPartialTicks());
        }
    }

    private void doEsp(Entity entity, float partialTicks) {
//        if (entity.equals(mc.thePlayer) || !entityCheck(entity))
//            r
        if (entity.equals(mc.thePlayer))
            return;

        final double x = MathUtil.lerp(entity.lastTickPosX, entity.posX, partialTicks) - RenderManager.renderPosX;
        final double y = MathUtil.lerp(entity.lastTickPosY, entity.posY, partialTicks) - RenderManager.renderPosY;
        final double z = MathUtil.lerp(entity.lastTickPosZ, entity.posZ, partialTicks) - RenderManager.renderPosZ;

        Color color = getGavhack().getFriendManager().isFriend(entity.getEntityName())
                ? Color.CYAN : getDistanceFade((int) mc.thePlayer.getDistanceToEntity(entity), 50);

        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 1f);
        GL11.glLineWidth(lineWidth.getValue());

        final Tessellator tessellator = Tessellator.instance;

        if (tracers.getValue()) {
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL32.GL_DEPTH_CLAMP);
            tessellator.startDrawing(GL11.GL_LINES);
            tessellator.addVertex(ActiveRenderInfo.objectX, ActiveRenderInfo.objectY, ActiveRenderInfo.objectZ);
            tessellator.addVertex(x, y, z);
            tessellator.draw();
            GL11.glDisable(GL32.GL_DEPTH_CLAMP);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }

        if (boxes.getValue()) {
            final float halfWidth = entity.width / 2.0f;
            AxisAlignedBB lerpBox = AxisAlignedBB.getBoundingBox(
                    x - halfWidth, y, z - halfWidth,
                    x + halfWidth, y + entity.height, z + halfWidth
            );

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glTranslated(x, y, z);
            GL11.glRotatef(-(float)MathUtil.lerp(entity.prevRotationYaw, entity.rotationYaw, partialTicks), 0f, 1f, 0f);
            GL11.glTranslated(-x, -y, -z);
            RenderGlobal.drawOutlinedBoundingBox(lerpBox);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }

        GL11.glLineWidth(1f);
    }

    private Color getDistanceFade(int distance, int max) {
        final float normal = MathUtil.normalize(distance, 0f, max);
        return new Color(1f - normal, normal, 0f);
    }
}
