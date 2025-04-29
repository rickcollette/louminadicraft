package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * AcidStormManager - Triggers acid storms randomly in MoneyShot world
 */
public class AcidStormManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();
    private boolean stormActive = false;

    public AcidStormManager(JavaPlugin plugin) {
        this.plugin = plugin;
        startStormTask();
    }

    private void startStormTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("world_moneyshot");
                if (world == null) {
                    return;
                }

                if (!stormActive && random.nextInt(100) == 0) { // 1% chance every check
                    startAcidStorm(world);
                }
            }
        }.runTaskTimer(plugin, 0L, 600L); // Check every 30 seconds (600 ticks)
    }

    private void startAcidStorm(World world) {
        stormActive = true;

        int stormDurationSeconds = 30 + random.nextInt(30); // 30–60 seconds

        // Set weather
        world.setStorm(true);
        world.setWeatherDuration(stormDurationSeconds * 20);

        // Apply potion effects to all players
        for (Player player : world.getPlayers()) {
            player.sendMessage("§2⚡ A toxic acid storm is rolling in! Take shelter!");
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, stormDurationSeconds * 20, 0, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, stormDurationSeconds * 20, 0, false, false));
        }

        // End storm after duration
        new BukkitRunnable() {
            @Override
            public void run() {
                stormActive = false;
                world.setStorm(false);

                for (Player player : world.getPlayers()) {
                    player.sendMessage("§7The acid storm fades away...");
                }
            }
        }.runTaskLater(plugin, stormDurationSeconds * 20L);
    }
}
