package dev.gavhack.manager;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PacketEvent;
import dev.gavhack.util.internal.Timer;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.GuiPlayerInfo;
import net.minecraft.src.Packet4UpdateTime;

import java.util.Optional;

public class ServerManager implements Wrapper {
    private final Timer lagTimer = new Timer();

    public ServerManager() {
        EventManager.register(this);
    }

    @EventTarget
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof Packet4UpdateTime) {
            lagTimer.reset();
        }
    }

    public int getLatency(String username) {
        Optional<GuiPlayerInfo> info = mc.getNetHandler().playerInfoList.stream()
                .filter((inf) -> inf.name.equals(username))
                .findFirst();

        return info.map(guiPlayerInfo -> guiPlayerInfo.responseTime).orElse(0);
    }

    public long getLagTime() {
        return isLagging() ? 0L : lagTimer.getTimeMs();
    }

    public boolean isLagging() {
        return lagTimer.passed(1500L);
    }
}
