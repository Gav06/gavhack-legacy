package dev.gavhack.features.module.render;

import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.EventPacket;
import dev.gavhack.event.EventRenderWorld;
import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import dev.gavhack.setting.Setting;
import net.minecraft.src.Chunk;
import net.minecraft.src.Packet51MapChunk;
import net.minecraft.src.RenderManager;
import org.lwjgl.opengl.GL11;

import java.util.HashSet;
import java.util.Set;

public class NewChunks extends Module {

    private final Setting<Integer> clearSize = new Setting<>("ClearSize", 10000);

    private boolean needsRebuild = true;
    private int displayList = GL11.glGenLists(GL11.GL_ONE);

    private final Set<Chunk> chunks = new HashSet<>();

    public NewChunks() {
        super("NewChunks", Category.RENDER);
    }

    @EventTarget
    public void onWorldRender(EventRenderWorld event) {
        if (needsRebuild) {
            GL11.glNewList(displayList, GL11.GL_COMPILE);

            for (Chunk chunk : chunks) {
                GL11.glBegin(GL11.GL_LINE_LOOP);
                GL11.glVertex3i(chunk.xPosition*16, 0, chunk.zPosition*16);
                GL11.glVertex3i(chunk.xPosition*16 + 16, 0, chunk.zPosition*16);
                GL11.glVertex3i(chunk.xPosition*16 + 16, 0, chunk.zPosition*16 + 16);
                GL11.glVertex3i(chunk.xPosition*16, 0, chunk.zPosition*16 + 16);
                GL11.glEnd();
            }

            GL11.glEndList();
        }

        GL11.glPushMatrix();
        GL11.glLineWidth(2f);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glColor4f(1f, 0f, 0f, 1f);
        GL11.glTranslated(-RenderManager.renderPosX, -RenderManager.renderPosY, -RenderManager.renderPosZ);
        GL11.glCallList(displayList);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    @EventTarget
    public void onPacketRead(EventPacket.Receive event) {
        if (event.getPacket() instanceof Packet51MapChunk) {
            final Packet51MapChunk packet = (Packet51MapChunk) event.getPacket();
            if (packet.includeInitialize) {
                if (chunks.size() > clearSize.getValue())
                    chunks.clear();

                chunks.add(mc.theWorld.getChunkFromChunkCoords(packet.xCh, packet.zCh));
            }
        }
    }
}
