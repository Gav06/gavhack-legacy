package dev.gavhack.manager.friend;

import net.minecraft.src.EntityPlayer;

import java.util.HashSet;

public class FriendManager {
    // using a set so that we dont have more than 1 of the same entry
    private final HashSet<Friend> friends = new HashSet<>();

    public boolean isFriend(String username) {
        return friends.stream().anyMatch((friend) -> friend.getUsername().equals(username));
    }

    public void add(EntityPlayer player) {
        friends.add(new Friend(player.getEntityName(), player.getEntityName()));
    }

    public void add(EntityPlayer player, String alias) {
        friends.add(new Friend(player.getEntityName(), alias));
    }

    public void remove(EntityPlayer player) {
        friends.removeIf((friend) -> friend.getUsername().equals(player.getEntityName()));
    }

    public HashSet<Friend> getFriends() {
        return friends;
    }
}
