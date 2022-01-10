package dev.gavhack.features.module.render;

import dev.gavhack.features.module.Category;
import dev.gavhack.features.module.Module;
import net.minecraft.src.Block;
import org.lwjgl.input.Keyboard;

import java.util.HashSet;

public class Wallhack extends Module {

    public static Wallhack INSTANCE;

    public static final HashSet<Integer> blocks = new HashSet<>();

    static {
        blocks.add(Block.oreDiamond.blockID);
        blocks.add(Block.oreCoal.blockID);
        blocks.add(Block.oreIron.blockID);
        blocks.add(Block.oreRedstone.blockID);
        blocks.add(Block.oreGold.blockID);
        blocks.add(Block.oreLapis.blockID);
        blocks.add(Block.oreNetherQuartz.blockID);
    }

    public Wallhack() {
        super("Wallhack", Category.RENDER);
        INSTANCE = this;
    }

    @Override
    protected void onEnable() {
        if (mc.renderGlobal != null) {
            mc.renderGlobal.loadRenderers();
        }
    }

    @Override
    protected void onDisable() {
        if (mc.renderGlobal != null) {
            mc.renderGlobal.loadRenderers();
        }
    }
}
