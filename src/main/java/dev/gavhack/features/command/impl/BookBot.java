package dev.gavhack.features.command.impl;

import dev.gavhack.features.command.Command;
import dev.gavhack.util.internal.ChatUtil;
import net.minecraft.src.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class BookBot extends Command {
    public BookBot() {
        super(Arrays.asList("dupebook", "bookbot"), "Generate random books for the chunk dupe");
    }

    @Override
    public void run(List<String> args) {
        if (args.size() != 2) {
            ChatUtil.sendPrefixed("Please specify 2 arguments (title, random seed)");
        }

        String title = args.get(0);
        int seed;

        try {
            seed = Integer.parseInt(args.get(1));
        } catch (NumberFormatException e) {
            ChatUtil.sendPrefixed("Random seed must be an integer");
            return;
        }

        if (mc.thePlayer.getHeldItem() == null || !(mc.thePlayer.getHeldItem().getItem() instanceof ItemWritableBook)) {
            ChatUtil.sendPrefixed("You need to be holding a book and quill");
            return;
        }

        // thanks medmex

        if (!mc.thePlayer.getHeldItem().hasTagCompound())
            mc.thePlayer.getHeldItem().stackTagCompound = new NBTTagCompound();

        if (!mc.thePlayer.getHeldItem().getTagCompound().hasKey("pages"))
            mc.thePlayer.getHeldItem().stackTagCompound.setTag("pages", new NBTTagList());

        Random random = new Random(seed);

        final int charBegin = 0x0800;
        final int charEnd = 0x10FFFF;

        // loop for 50 pages
        for (int i = 0; i < 50; i++) {
            StringBuilder line = new StringBuilder();
            IntStream istream = random.ints(256, charBegin, charEnd);
            for (int rand : istream.toArray()) {
                char randChar = (char) rand;
                line.append(randChar);
            }

            mc.thePlayer.getHeldItem().stackTagCompound.getTagList("pages").appendTag(new NBTTagString(String.valueOf(i), line.toString()));
        }

        GuiScreenBook bookScreen = new GuiScreenBook(mc.thePlayer, mc.thePlayer.getHeldItem(), true);
        bookScreen.sendBookToServer(true, title, mc.thePlayer.getCommandSenderName());

        ChatUtil.sendPrefixed("Wrote random book successfully");
    }
}
