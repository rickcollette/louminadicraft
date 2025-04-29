package com.louminadicraft.command;

import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * BiomeInfoCommand - Shows what biome the player is standing in.
 */
public class BiomeInfoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        Biome biome = player.getLocation().getBlock().getBiome();

        player.sendMessage("You are currently in the biome: §a" + biome.name());
        return true;
    }
}
