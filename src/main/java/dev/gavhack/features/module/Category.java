package dev.gavhack.features.module;

public enum Category {
    COMBAT("Combat"),
    HUD("HUD"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render"),
    WORLD("World"),
    CLIENT("Client");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}