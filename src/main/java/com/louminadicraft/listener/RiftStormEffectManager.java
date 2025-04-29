package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * RiftStormEffectManager - Applies random unstable effects near rifts
 */
public class RiftStormEffectManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public RiftStormEffectManager(JavaPlugin plugin) {
        this.plugin = plugin;
        startRiftStormTask();
    }

    private void startRiftStormTask() {
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
                        applyRandomRiftEffect(player);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 100L); // Every 5 seconds
    }

    private void applyRandomRiftEffect(Player player) {
        int pick = random.nextInt(4);

        switch (pick) {
            case 0 -> player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 60, 0, false, false));
            case 1 -> player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0, false, false));
            case 2 -> player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 120, 1, false, false));
            case 3 -> player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 1, false, false));
        }
    }
}
