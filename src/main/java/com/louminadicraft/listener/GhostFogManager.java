package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * GhostFogManager - Creates heavy ghostly fog bursts in Conditions world
 */
public class GhostFogManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public GhostFogManager(JavaPlugin plugin) {
        this.plugin = plugin;
        startFogTask();
    }

    private void startFogTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();

                    if (!world.getName().toLowerCase().contains("conditions")) {
                        continue;
                    }

                    if (random.nextInt(8) != 0) { // Rare
                        continue;
                    }

                    // Spawn fog cloud around player
                    world.spawnParticle(
                        Particle.SOUL,
                        player.getLocation(),
                        100,
                        5, 2, 5,
                        0.1
                    );
                }
            }
        }.runTaskTimer(plugin, 0L, 100L); // Every 5 seconds
    }
}
