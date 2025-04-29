package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * MoneyShotSoundListener - Ambient spooky sounds in MoneyShot world
 */
public class MoneyShotSoundListener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public MoneyShotSoundListener(JavaPlugin plugin) {
        this.plugin = plugin;
        startSoundTask();
    }

    private void startSoundTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();

                    if (!world.getName().toLowerCase().contains("moneyshot")) {
                        continue;
                    }

                    if (random.nextInt(10) != 0) { // 10% chance every cycle
                        continue;
                    }

                    Sound selectedSound = pickRandomAmbientSound();

                    player.playSound(
                        player.getLocation(),
                        selectedSound,
                        0.6f, // volume
                        0.8f + random.nextFloat() * 0.4f // pitch between 0.8 and 1.2
                    );
                }
            }
        }.runTaskTimer(plugin, 0L, 100L); // every 5 seconds (100 ticks)
    }

    private Sound pickRandomAmbientSound() {
        Sound[] sounds = {
            Sound.AMBIENT_CAVE,             // Deep eerie cave echo
            Sound.ENTITY_PHANTOM_AMBIENT,   // Creepy phantom sounds
            Sound.ENTITY_GHAST_AMBIENT,     // Distant ghastly crying
            Sound.ENTITY_ENDERMAN_AMBIENT,  // Weird alien-like sounds
            Sound.WEATHER_RAIN_ABOVE        // Very soft "distant rain" noise
        };

        return sounds[random.nextInt(sounds.length)];
    }
}
