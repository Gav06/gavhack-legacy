package dev.gavhack.event;

import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;

public class AttackEntityEvent extends EventCancellable {

    private final EntityPlayer attacker;
    private Entity target;

    public AttackEntityEvent(EntityPlayer attacker, Entity target) {
        this.attacker = attacker;
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }

    public EntityPlayer getAttacker() {
        return attacker;
    }
}
