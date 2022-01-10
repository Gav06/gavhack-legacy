package net.minecraft.src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Packet13PlayerLookMove extends Packet10Flying
{
    public Packet13PlayerLookMove()
    {
        this.rotating = true;
        this.moving = true;
    }

    public Packet13PlayerLookMove(double x, double y, double stance, double z, float yaw, float pitch, boolean onGround)
    {
        this.xPosition = x;
        this.yPosition = y;
        this.stance = stance;
        this.zPosition = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.rotating = true;
        this.moving = true;
    }

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInput par1DataInput) throws IOException
    {
        this.xPosition = par1DataInput.readDouble();
        this.yPosition = par1DataInput.readDouble();
        this.stance = par1DataInput.readDouble();
        this.zPosition = par1DataInput.readDouble();
        this.yaw = par1DataInput.readFloat();
        this.pitch = par1DataInput.readFloat();
        super.readPacketData(par1DataInput);
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutput par1DataOutput) throws IOException
    {
        par1DataOutput.writeDouble(this.xPosition);
        par1DataOutput.writeDouble(this.yPosition);
        par1DataOutput.writeDouble(this.stance);
        par1DataOutput.writeDouble(this.zPosition);
        par1DataOutput.writeFloat(this.yaw);
        par1DataOutput.writeFloat(this.pitch);
        super.writePacketData(par1DataOutput);
    }

    /**
     * Abstract. Return the size of the packet (not counting the header).
     */
    public int getPacketSize()
    {
        return 41;
    }
}
