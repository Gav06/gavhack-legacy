package dev.gavhack.features.module.client;

import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.EntityOtherPlayerMP;

public class FakePlayer extends Module {

    private EntityOtherPlayerMP fakePlayer = null;

    public FakePlayer() {
        super("FakePlayer", Category.CLIENT);
    }

    @Override
    protected void onEnable() {
        if (mc.theWorld == null) {
            disable();
            return;
        }

        fakePlayer = new EntityOtherPlayerMP(mc.theWorld, "Gav06");
        fakePlayer.posX = mc.thePlayer.posX;
        fakePlayer.posY = mc.thePlayer.boundingBox.minY;
        fakePlayer.posZ = mc.thePlayer.posZ;
        fakePlayer.rotationYaw = mc.thePlayer.rotationYaw;
        fakePlayer.prevRotationYaw = mc.thePlayer.prevRotationYaw;
        fakePlayer.rotationPitch = mc.thePlayer.prevRotationPitch;
        fakePlayer.prevRotationPitch = mc.thePlayer.prevRotationPitch;
        mc.theWorld.addEntityToWorld(-6969, fakePlayer);
    }

    @Override
    protected void onDisable() {
        if (fakePlayer == null)
            return;

        mc.theWorld.removeEntity(fakePlayer);
        fakePlayer = null;
    }
}
