package dev.gavhack.manager.friend;

import net.minecraft.src.EntityPlayer;

import java.util.ArrayList;
import java.util.UUID;

public class FriendManager {
    private final ArrayList<Friend> friends = new ArrayList<>();

    public boolean isFriend(UUID uuid) {
        return friends.stream().anyMatch((friend) -> friend.getUuid().equals(uuid));
    }

    public void add(EntityPlayer player) {
        friends.add(new Friend(player.getUniqueID(), player.getEntityName()));
    }

    public void remove(EntityPlayer player) {
        friends.removeIf((friend) -> friend.getUuid().equals(player.getUniqueID()));
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }
}
