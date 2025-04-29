package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * AmbientThunderListener - Ambient thunder rumbling across all worlds
 */
public class AmbientThunderListener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public AmbientThunderListener(JavaPlugin plugin) {
        this.plugin = plugin;
        startThunderTask();
    }

    private void startThunderTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();

                    if (random.nextInt(20) != 0) { // ~5% chance every cycle
                        continue;
                    }

                    player.playSound(
                        player.getLocation(),
                        Sound.ENTITY_LIGHTNING_BOLT_THUNDER,
                        0.4f,
                        0.7f + random.nextFloat() * 0.3f
                    );
                }
            }
        }.runTaskTimer(plugin, 0L, 200L); // Every 10 seconds
    }
}
