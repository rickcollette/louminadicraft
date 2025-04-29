package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * MemoryEchoUnlocker - Unlock ancient memories scattered through ruins
 */
public class MemoryEchoUnlocker implements Listener {

    private final JavaPlugin plugin;
    private final Set<UUID> recentlyUnlocked = new HashSet<>();

    public MemoryEchoUnlocker(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.getWorld().getName().toLowerCase().contains("existential")) {
            return;
        }

        if (recentlyUnlocked.contains(player.getUniqueId())) {
            return;
        }

        Block blockBelow = player.getLocation().subtract(0, 1, 0).getBlock();
        if (blockBelow.getType() == Material.LODESTONE) {
            unlockMemory(player);
        }
    }

    private void unlockMemory(Player player) {
        recentlyUnlocked.add(player.getUniqueId());
        player.sendMessage("§b⚡ You have unlocked a Memory Echo of the Ancients...");

        new BukkitRunnable() {
            @Override
            public void run() {
                recentlyUnlocked.remove(player.getUniqueId());
            }
        }.runTaskLater(plugin, 600L); // 30 sec cooldown
    }
}
