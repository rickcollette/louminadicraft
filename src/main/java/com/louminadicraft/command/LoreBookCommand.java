package com.louminadicraft.command;

import com.louminadicraft.quest.ExistentialLoreBook;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * /lorebook command - Give current lore book
 */
public class LoreBookCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can receive the Lore Book.");
            return true;
        }

        ExistentialLoreBook.giveLoreBook(player);
        return true;
    }
}
