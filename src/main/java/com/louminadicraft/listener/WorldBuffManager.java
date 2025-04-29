package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * WorldBuffManager - Applies world-specific player buffs
 */
public class WorldBuffManager {

    private final JavaPlugin plugin;

    public WorldBuffManager(JavaPlugin plugin) {
        this.plugin = plugin;
        startBuffTask();
    }

    private void startBuffTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();
                    String name = world.getName().toLowerCase();

                    // Clear all controlled effects first
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.removePotionEffect(PotionEffectType.JUMP_BOOST);
                    player.removePotionEffect(PotionEffectType.WEAKNESS);

                    if (name.contains("billyd")) {
                        applyBillyDBuffs(player);
                    } else if (name.contains("conditions")) {
                        applyConditionsDebuffs(player);
                    }
                    // MoneyShot: no buffs or debuffs
                }
            }
        }.runTaskTimer(plugin, 0L, 200L); // every 10 seconds (200 ticks)
    }

    private void applyBillyDBuffs(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 240, 1, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 240, 1, false, false));
    }

    private void applyConditionsDebuffs(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 240, 0, false, false));
    }
}
