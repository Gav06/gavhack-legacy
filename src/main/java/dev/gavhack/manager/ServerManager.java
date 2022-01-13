package dev.gavhack.manager;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.gavhack.event.PacketEvent;
import dev.gavhack.util.internal.Timer;
import dev.gavhack.util.internal.Wrapper;
import net.minecraft.src.GuiPlayerInfo;

import java.util.Optional;

public class ServerManager implements Wrapper {
    private final Timer lagTimer = new Timer();

    public ServerManager() {
        EventManager.register(this);
    }

    @EventTarget
    public void onPacketReceive(PacketEvent.Receive event) {
        lagTimer.reset();
    }

    public int getLatency(String username) {
        Optional<GuiPlayerInfo> info = mc.getNetHandler().playerInfoList.stream()
                .filter((inf) -> inf.name.equals(username))
                .findFirst();

        return info.map(guiPlayerInfo -> guiPlayerInfo.responseTime).orElse(0);
    }

    public long getLagTime() {
        return isLagging() ? lagTimer.getTimeMs() : 0L;
    }

    public boolean isLagging() {
        return lagTimer.passed(getLatency(mc.thePlayer.getEntityName()) + 500L);
    }
}
