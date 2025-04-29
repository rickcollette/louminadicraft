package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * RiftMusicManager - Plays Louminadi music near fractures
 */
public class RiftMusicManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public RiftMusicManager(JavaPlugin plugin) {
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

                    Location loc = player.getLocation();
                    Location below = loc.clone().subtract(0, 1, 0);

                    if (below.getBlock().getType() == Material.LODESTONE) {
                        playRiftMusic(player);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 400L); // Every 20 seconds
    }

    private void playRiftMusic(Player player) {
        Sound[] music = {
            Sound.MUSIC_DISC_PIGSTEP,
            Sound.MUSIC_DISC_OTHERSIDE,
            Sound.MUSIC_DISC_RELIC
        };

        player.playSound(player.getLocation(), music[random.nextInt(music.length)], 1.0f, 1.0f);
    }
}
