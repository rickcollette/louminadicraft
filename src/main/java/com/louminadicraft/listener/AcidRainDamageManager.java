package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * AcidRainDamageManager - Deals damage during acid storms if players are not sheltered
 */
public class AcidRainDamageManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public AcidRainDamageManager(JavaPlugin plugin) {
        this.plugin = plugin;
        startDamageTask();
    }

    private void startDamageTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();

                    if (!world.getName().toLowerCase().contains("moneyshot")) {
                        continue;
                    }

                    if (!world.hasStorm()) {
                        continue;
                    }

                    if (isSheltered(player)) {
                        continue;
                    }

                    // Small acid burn
                    player.damage(1.0); // Half-heart
                    player.sendActionBar("§c☠️ The acid rain burns your skin!");
                }
            }
        }.runTaskTimer(plugin, 0L, 100L); // Every 5 seconds
    }

    private boolean isSheltered(Player player) {
        var eyeLoc = player.getEyeLocation();
        var blockAbove = eyeLoc.add(0, 1, 0).getBlock();
        return blockAbove.getType() != Material.AIR;
    }
}
