package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * RiftWarpGateListener - Allow teleport via end gateways
 */
public class RiftWarpGateListener implements Listener {

    public RiftWarpGateListener(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerUseWarpGate(PlayerInteractEvent event) {
        var clickedBlock = event.getClickedBlock();
        var player = event.getPlayer();

        if (clickedBlock == null) return;

        if (clickedBlock.getType() != Material.END_GATEWAY) return;

        World world = clickedBlock.getWorld();
        if (world == null || !world.getName().toLowerCase().contains("existential")) {
            return;
        }

        // Teleport the player a random ~500–1000 blocks away
        Location dest = player.getLocation().clone().add(
            (Math.random() - 0.5) * 1000,
            0,
            (Math.random() - 0.5) * 1000
        );

        dest.setY(world.getHighestBlockYAt(dest));

        player.teleport(dest);
        player.sendMessage("§d⚡ You are warped across the Existential realm!");
    }
}
