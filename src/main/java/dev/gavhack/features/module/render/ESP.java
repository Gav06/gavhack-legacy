package dev.gavhack.features.module.render;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.RenderWorldEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.setting.Setting;
import dev.gavhack.util.entity.EntityUtil;
import dev.gavhack.util.math.MathUtil;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL32;

import java.awt.*;

public class ESP extends Module {
    public ESP() {
        super("ESP", Category.RENDER);
    }

    public static final Setting<Boolean> invisible = new Setting<>("Invisible", true);
    public static final Setting<Boolean> players = new Setting<>("Players", true);
    public static final Setting<Boolean> mobs = new Setting<>("Mobs", false);
    public static final Setting<Boolean> passive = new Setting<>("Passive", true);

    public final Setting<Boolean> tracers = new Setting<>("Tracers", true);
    public final Setting<Boolean> boxes = new Setting<>("Boxes", true);

    public final Setting<Boolean> onlyWhite = new Setting<>("OnlyWhite", false);
    public final Setting<Float> lineWidth = new Setting<>("LineWidth", 1.0f, 1.0f, 10f);

    @EventTarget
    public void onRender(RenderWorldEvent event) {
        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (!(entity instanceof EntityLivingBase) || entity.equals(mc.thePlayer)) {
                continue;
            }

            EntityLivingBase livingBase = (EntityLivingBase) entity;

            if (!invisible.getValue() && EntityUtil.isInvisible(livingBase)) {
                continue;
            }

            if (!players.getValue() && entity instanceof EntityPlayer) {
                continue;
            }

            if (!passive.getValue() && EntityUtil.isPassive(livingBase)) {
                continue;
            }

            if (!mobs.getValue() && EntityUtil.isMob(livingBase)) {
                continue;
            }

            double x = MathUtil.lerp(entity.lastTickPosX, entity.posX, event.getPartialTicks()) - RenderManager.renderPosX;
            double y = MathUtil.lerp(entity.lastTickPosY, entity.posY, event.getPartialTicks()) - RenderManager.renderPosY;
            double z = MathUtil.lerp(entity.lastTickPosZ, entity.posZ, event.getPartialTicks()) - RenderManager.renderPosZ;

            Color color = getColor(livingBase);
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
                GL11.glRotatef(-(float) MathUtil.lerp(entity.prevRotationYaw, entity.rotationYaw, event.getPartialTicks()), 0f, 1f, 0f);
                GL11.glTranslated(-x, -y, -z);
                RenderGlobal.drawOutlinedBoundingBox(lerpBox);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glPopMatrix();
            }

            GL11.glLineWidth(1f);
        }
    }

    private Color getColor(EntityLivingBase entity) {
        if (onlyWhite.getValue()) {
            return Color.WHITE;
        }

        if (entity instanceof EntityPlayer) {
            if (getGavhack().getFriendManager().isFriend(entity.getEntityName())) {
                return Color.CYAN;
            }

            return getDistanceFade((int) mc.thePlayer.getDistanceToEntity(entity), 50);
        }

        if (EntityUtil.isInvisible(entity)) {
            return Color.LIGHT_GRAY;
        }

        if (EntityUtil.isPassive(entity)) {
            return Color.GREEN;
        }

        if (EntityUtil.isMob(entity)) {
            return Color.ORANGE;
        }

        return Color.WHITE;
    }

    private Color getDistanceFade(int distance, int max) {
        final float normal = MathUtil.normalize(distance, 0f, max);
        return new Color(1f - normal, normal, 0f);
    }
}
