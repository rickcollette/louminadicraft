package com.louminadicraft.command;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * WarpCommand - Handles /warp conditions and /warp moneyshot
 */
public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /warp <conditions|moneyshot>");
            return true;
        }

        String target = args[0].toLowerCase();

        if (target.equals("conditions")) {
            warpToWorld(player, "world_conditions");
        } else if (target.equals("moneyshot")) {
            warpToWorld(player, "world_moneyshot");
        } else {
            player.sendMessage("Unknown warp target: " + target);
            player.sendMessage("Usage: /warp <conditions|moneyshot>");
        }

        return true;
    }

    private void warpToWorld(Player player, String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            player.sendMessage("World " + worldName + " is not loaded.");
            return;
        }

        Location spawn = world.getSpawnLocation();
        player.teleport(spawn);
        player.sendMessage("Warped to " + worldName + "!");
    }
}
