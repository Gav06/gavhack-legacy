package dev.gavhack.features.module.render;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.Render2dEvent;
import dev.gavhack.event.RenderWorldEvent;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.util.math.ProjectionUtil;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class NameTags extends Module {

    public NameTags() {
        super("NameTags", Category.RENDER);
    }

    @EventTarget
    public void onRenderPre(RenderWorldEvent event){
        for (EntityPlayer player : mc.theWorld.playerEntities) {
            if (player == mc.thePlayer)
                continue;

            final float health = player.getHealth() + player.getAbsorptionAmount();
            final String text = (player.isSneaking() ? EnumChatFormatting.GOLD : "")
                    + player.getEntityName() + EnumChatFormatting.RESET + " "
                    + getHealthFormatting(health) + health;

            double scale = (0.0083333 * (mc.thePlayer.getDistanceToEntity(player) / 2));

            GL11.glPushMatrix();
            GL11.glTranslated(
                    player.posX - RenderManager.renderPosX,
                    player.posY + player.height + 0.3 - RenderManager.renderPosY,
                    player.posZ - RenderManager.renderPosZ);
            GL11.glNormal3f(0f, 1f, 0f);
            GL11.glRotatef(-RenderManager.instance.playerViewY, 0f, 1f, 0f);
            GL11.glRotatef(RenderManager.instance.playerViewX, 1f, 0f, 0f);
            GL11.glScaled(-scale, -scale, scale);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            Gui.drawRect(
                    -(mc.fontRenderer.getStringWidth(text) / 2) - 2,
                    -mc.fontRenderer.FONT_HEIGHT - 2,
                    (mc.fontRenderer.getStringWidth(text) / 2) + 2,
                    (mc.fontRenderer.FONT_HEIGHT / 2) - 3, 0x80000000);
            mc.fontRenderer.drawStringWithShadow(text, -(mc.fontRenderer.getStringWidth(text) / 2), -mc.fontRenderer.FONT_HEIGHT, -1);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glPopMatrix();
        }
    }

    private EnumChatFormatting getHealthFormatting(float health) {
        if (health >= 15f) {
            return EnumChatFormatting.GREEN;
        } else if (health >= 10f) {
            return EnumChatFormatting.YELLOW;
        } else if (health >= 5f) {
            return EnumChatFormatting.RED;
        } else {
            return EnumChatFormatting.DARK_RED;
        }
    }
}
