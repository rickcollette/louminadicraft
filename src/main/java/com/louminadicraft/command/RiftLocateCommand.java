package com.louminadicraft.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * RiftLocateCommand - Find the nearest Fracture Rift
 */
public class RiftLocateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        World world = player.getWorld();

        if (!world.getName().toLowerCase().contains("existential")) {
            player.sendMessage("This command can only be used in the Existential world.");
            return true;
        }

        Location loc = player.getLocation();
        int scanRadius = 300;

        Block nearestRift = null;
        double nearestDistance = Double.MAX_VALUE;

        for (int dx = -scanRadius; dx <= scanRadius; dx++) {
            for (int dz = -scanRadius; dz <= scanRadius; dz++) {
                Location checkLoc = loc.clone().add(dx, 0, dz);
                checkLoc.setY(65); // typical surface

                Block block = checkLoc.getBlock();

                if (block.getType() == org.bukkit.Material.LODESTONE) {
                    double dist = loc.distance(block.getLocation());
                    if (dist < nearestDistance) {
                        nearestDistance = dist;
                        nearestRift = block;
                    }
                }
            }
        }

        if (nearestRift != null) {
            player.sendMessage("§dNearest Fracture Rift found at: §f" + nearestRift.getX() + ", " + nearestRift.getY() + ", " + nearestRift.getZ());
        } else {
            player.sendMessage("§cNo rifts found nearby.");
        }

        return true;
    }
}
