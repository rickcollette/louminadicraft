package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * BillyDJungleSoundListener - Ambient jungle sounds in Billy D world
 */
public class BillyDJungleSoundListener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public BillyDJungleSoundListener(JavaPlugin plugin) {
        this.plugin = plugin;
        startJungleSoundTask();
    }

    private void startJungleSoundTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();

                    if (!world.getName().toLowerCase().contains("billyd")) {
                        continue;
                    }

                    if (random.nextInt(6) != 0) { // 1/6 chance every check
                        continue;
                    }

                    Sound selectedSound = pickRandomJungleSound();

                    player.playSound(
                        player.getLocation(),
                        selectedSound,
                        0.6f,
                        0.9f + random.nextFloat() * 0.2f
                    );
                }
            }
        }.runTaskTimer(plugin, 0L, 100L); // every 5 seconds
    }

    private Sound pickRandomJungleSound() {
        Sound[] sounds = {
            Sound.ENTITY_PARROT_AMBIENT,     // Jungle birds
            Sound.ENTITY_PARROT_IMITATE_SPIDER,
            Sound.ENTITY_PARROT_IMITATE_ZOMBIE,
            Sound.ENTITY_PARROT_IMITATE_CREEPER,
            Sound.AMBIENT_CAVE                // Soft jungle cave echoes
        };

        return sounds[random.nextInt(sounds.length)];
    }
}
