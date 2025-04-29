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
 * EarthquakeManager - Random earthquakes in Conditions world
 */
public class EarthquakeManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public EarthquakeManager(JavaPlugin plugin) {
        this.plugin = plugin;
        startEarthquakeTask();
    }

    private void startEarthquakeTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("world_conditions");
                if (world == null) {
                    return;
                }

                if (random.nextInt(30) != 0) { // ~Once every 5 minutes
                    return;
                }

                triggerEarthquake(world);
            }
        }.runTaskTimer(plugin, 0L, 6000L); // Check every 5 minutes (6000 ticks)
    }

    private void triggerEarthquake(World world) {
        for (Player player : world.getPlayers()) {
            player.sendMessage("§6⚡ The ground trembles violently!");
            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.2f, 0.6f);
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 0.7f);

            player.addPotionEffect(new org.bukkit.potion.PotionEffect(
                org.bukkit.potion.PotionEffectType.NAUSEA, 100, 0, false, false
            ));
            player.addPotionEffect(new org.bukkit.potion.PotionEffect(
                org.bukkit.potion.PotionEffectType.SLOWNESS, 100, 1, false, false
            ));

            // Shake effect (optional particles)
            player.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, player.getLocation(), 30, 1, 1, 1, 0.05);
        }
    }
}
