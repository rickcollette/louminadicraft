package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Random;

/**
 * Existential Music plays near Shrines
 */
public class ExistentialMusicListener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public ExistentialMusicListener(JavaPlugin plugin) {
        this.plugin = plugin;
        startMusicTask();
    }

    private void startMusicTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();
                    if (!world.getName().toLowerCase().contains("existential")) {
                        continue;
                    }

                    if (random.nextInt(10) != 0) continue; // 10% chance

                    player.playSound(player.getLocation(), Sound.MUSIC_DISC_PIGSTEP, 1.0f, 0.8f + random.nextFloat() * 0.4f);
                }
            }
        }.runTaskTimer(plugin, 0L, 200L); // every 10 seconds
    }
}
