package net.minecraft.src;

import org.lwjgl.Sys;

import java.util.concurrent.Callable;

class CallableLWJGLVersion implements Callable
{
    /** Reference to the Minecraft object. */
    final Minecraft mc;

    CallableLWJGLVersion(Minecraft par1Minecraft)
    {
        this.mc = par1Minecraft;
    }

    public String getType()
    {
        return Sys.getVersion();
    }

    public Object call()
    {
        return this.getType();
    }
}
