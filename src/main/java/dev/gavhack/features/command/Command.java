package dev.gavhack.features.command;

import dev.gavhack.util.internal.Wrapper;

import java.util.List;

public abstract class Command implements Wrapper {
    private final List<String> triggers;
    private final String description;

    public Command(List<String> triggers, String description) {
        this.triggers = triggers;
        this.description = description;
    }

    public abstract void run(List<String> args);

    public List<String> getTriggers() {
        return triggers;
    }

    public boolean isTrigger(String trigger) {
        return triggers.stream().anyMatch((t) -> t.equalsIgnoreCase(trigger));
    }

    public String getDescription() {
        return description;
    }
}
