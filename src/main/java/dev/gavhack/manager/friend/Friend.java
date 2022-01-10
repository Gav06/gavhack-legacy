package dev.gavhack.manager.friend;

import java.util.UUID;

public class Friend {
    private final UUID uuid;
    private final String alias;

    public Friend(UUID uuid, String alias) {
        this.uuid = uuid;
        this.alias = alias;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Friend)) {
            return false;
        }

        return ((Friend) o).getUuid().equals(uuid);
    }
}
