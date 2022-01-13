package dev.gavhack.event;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.src.MovementInput;

public class InputUpdateEvent implements Event {
    private final MovementInput movementInput;

    public InputUpdateEvent(MovementInput movementInput) {
        this.movementInput = movementInput;
    }

    public MovementInput getMovementInput() {
        return movementInput;
    }
}
