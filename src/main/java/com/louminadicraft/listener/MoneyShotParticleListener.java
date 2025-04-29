package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * MoneyShotParticleListener - Spawns heavy fog particles constantly in MoneyShot.
 */
public class MoneyShotParticleListener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public MoneyShotParticleListener(JavaPlugin plugin) {
        this.plugin = plugin;
        startFogTask();
    }

    private void startFogTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();

                    if (!world.getName().toLowerCase().contains("moneyshot")) {
                        continue;
                    }

                    // Spawn constant fog around players
                    double offsetX = (random.nextDouble() - 0.5) * 4;
                    double offsetY = random.nextDouble() * 2;
                    double offsetZ = (random.nextDouble() - 0.5) * 4;

                    // Main heavy ash fog
                    player.getWorld().spawnParticle(
                        Particle.ASH,
                        player.getLocation().add(offsetX, offsetY, offsetZ),
                        20,
                        0.5, 0.7, 0.5,
                        0.02
                    );

                    // Occasionally spawn soul mist
                    if (random.nextInt(5) == 0) {
                        player.getWorld().spawnParticle(
                            Particle.SOUL,
                            player.getLocation().add(offsetX, offsetY, offsetZ),
                            10,
                            0.3, 0.5, 0.3,
                            0.01
                        );
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); // Run every 20 ticks (1 second)
    }
}
