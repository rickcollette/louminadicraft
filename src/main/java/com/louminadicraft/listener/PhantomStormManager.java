package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * PhantomStormManager - Spawns waves of Phantoms during acid storms in MoneyShot
 */
public class PhantomStormManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public PhantomStormManager(JavaPlugin plugin) {
        this.plugin = plugin;
        startPhantomTask();
    }

    private void startPhantomTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();

                    if (!world.getName().toLowerCase().contains("moneyshot")) {
                        continue;
                    }

                    if (!world.hasStorm()) {
                        continue;
                    }

                    if (random.nextInt(6) != 0) {
                        continue;
                    }

                    // Spawn a phantom near the player
                    Phantom phantom = (Phantom) world.spawnEntity(
                        player.getLocation().add(random.nextDouble() * 10 - 5, 10, random.nextDouble() * 10 - 5),
                        EntityType.PHANTOM
                    );
                    phantom.setCustomName("§5Storm Wraith");
                    phantom.setCustomNameVisible(true);
                    phantom.setGlowing(true);
                }
            }
        }.runTaskTimer(plugin, 0L, 100L); // Every 5 seconds
    }
}
