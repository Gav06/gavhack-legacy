package gavhack.event;

import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.src.Packet;

public class EventPacket extends EventCancellable {

    private final Packet packet;

    public EventPacket(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }

    public static class Send extends EventPacket {

        public Send(Packet packet) {
            super(packet);
        }
    }

    public static class Receive extends EventPacket {

        public Receive(Packet packet) {
            super(packet);
        }
    }
}
