package dev.gavhack.util.internal;

import net.minecraft.src.ChatMessageComponent;
import net.minecraft.src.EnumChatFormatting;

public class ChatUtil implements Wrapper {
    public static final String PREFIX = EnumChatFormatting.GREEN + "Gavhack[legacy] " + EnumChatFormatting.RESET;

    public static void sendPrefixed(String text) {
        mc.thePlayer.sendChatToPlayer(ChatMessageComponent.createFromText(PREFIX + text));
    }
}
