package com.louminadicraft.mobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Wealth Wraith Spawner - ghost mobs in MoneyShot world
 */
public class WealthWraithSpawner implements Listener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public WealthWraithSpawner(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        Location location = event.getLocation();
        World world = location.getWorld();

        if (world == null || !world.getName().toLowerCase().contains("moneyshot")) {
            return;
        }

        // 20% of Husk spawns become Wealth Wraiths
        if (event.getEntityType() == EntityType.HUSK && random.nextInt(5) == 0) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            entity.setCustomName("§7Wealth Wraith");
            entity.setCustomNameVisible(true);
            entity.setSilent(true);

            // Potion effects: Invisibility + slight glowing
            entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0, false, false));

            // Optional: reduce health
            entity.setMaxHealth(10);
            entity.setHealth(10);
        }
    }
}
