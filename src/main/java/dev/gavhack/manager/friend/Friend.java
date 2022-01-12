package dev.gavhack.manager.friend;

public class Friend {
    private final String username;
    private final String alias;

    public Friend(String username, String alias) {
        this.username = username;
        this.alias = alias;
    }

    public String getUsername() {
        return username;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Friend)) {
            return false;
        }

        return ((Friend) o).getUsername().equals(username);
    }
}
