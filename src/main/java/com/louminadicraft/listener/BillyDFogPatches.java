package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * BillyDFogPatches - Random dense fog patches in Billy D jungle
 */
public class BillyDFogPatches {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public BillyDFogPatches(JavaPlugin plugin) {
        this.plugin = plugin;
        startFogTask();
    }

    private void startFogTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();

                    if (!world.getName().toLowerCase().contains("billyd")) {
                        continue;
                    }

                    if (random.nextInt(6) != 0) {
                        continue;
                    }

                    // Spawn a fog patch near player
                    double offsetX = (random.nextDouble() - 0.5) * 20;
                    double offsetZ = (random.nextDouble() - 0.5) * 20;
                    double baseY = player.getLocation().getY() + 1;

                    world.spawnParticle(
                        Particle.CLOUD,
                        player.getLocation().add(offsetX, baseY, offsetZ),
                        50,
                        2.5, 1.5, 2.5,
                        0.02
                    );
                }
            }
        }.runTaskTimer(plugin, 0L, 100L); // Every 5 seconds
    }
}
