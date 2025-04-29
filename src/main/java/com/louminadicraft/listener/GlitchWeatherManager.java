package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * GlitchWeatherManager - Creates glitch storms in Existential Reckoning world.
 */
public class GlitchWeatherManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public GlitchWeatherManager(JavaPlugin plugin) {
        this.plugin = plugin;
        startGlitchStormTask();
    }

    private void startGlitchStormTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("world_existential");
                if (world == null) {
                    return;
                }

                if (random.nextInt(80) != 0) { // 1.25% chance every check
                    return;
                }

                triggerGlitchStorm(world);
            }
        }.runTaskTimer(plugin, 0L, 400L); // every 20 seconds
    }

    private void triggerGlitchStorm(World world) {
        // Visuals + sounds to create a glitchy feeling
        for (Player player : world.getPlayers()) {
            if (random.nextBoolean()) {
                player.getWorld().spawnParticle(
                    Particle.PORTAL,
                    player.getLocation(),
                    200,
                    2.5, 2.5, 2.5,
                    0.1
                );
            }

            if (random.nextBoolean()) {
                player.playSound(
                    player.getLocation(),
                    Sound.BLOCK_BEACON_DEACTIVATE,
                    0.6f,
                    0.3f + random.nextFloat() * 1.4f
                );
            }

            if (random.nextInt(5) == 0) { // Rarer
                player.playSound(
                    player.getLocation(),
                    Sound.ENTITY_ENDERMAN_SCREAM,
                    0.4f,
                    0.5f + random.nextFloat()
                );
            }
        }

        // Optional: you could set world.setWeatherDuration here if you want rain + storm
        world.setStorm(true);
        world.setWeatherDuration(20 * (30 + random.nextInt(30))); // 30-60 seconds glitch storm

        Bukkit.broadcastMessage("§5⚡ A reality storm glitches through the existential void...");
    }
}
