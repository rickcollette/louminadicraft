package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * WraithKingEvent - Spawns the Wraith King boss during acid storms in MoneyShot.
 */
public class WraithKingEvent implements Listener {

    private final JavaPlugin plugin;
    private final Random random = new Random();
    private boolean bossActive = false;

    public WraithKingEvent(JavaPlugin plugin) {
        this.plugin = plugin;
        startBossTask();
    }

    private void startBossTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("world_moneyshot");
                if (world == null) {
                    return;
                }

                if (!world.hasStorm()) {
                    bossActive = false;
                    return;
                }

                if (bossActive) {
                    return; // Only one at a time
                }

                if (random.nextInt(50) != 0) { // 2% chance every check
                    return;
                }

                spawnWraithKing(world);
            }
        }.runTaskTimer(plugin, 0L, 400L); // Check every 20 seconds (400 ticks)
    }

    private void spawnWraithKing(World world) {
        bossActive = true;

        Location spawnLocation = world.getSpawnLocation().add(random.nextInt(40) - 20, 2, random.nextInt(40) - 20);

        Zombie king = (Zombie) world.spawnEntity(spawnLocation, EntityType.ZOMBIE);
        king.setCustomName("§6👑 Wraith King of Greed");
        king.setCustomNameVisible(true);
        king.setAdult();
        king.setGlowing(true);
        king.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(300.0);
        king.setHealth(300.0);
        king.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(15.0);
        king.setSilent(true);
        king.getEquipment().setHelmet(new org.bukkit.inventory.ItemStack(org.bukkit.Material.GOLD_BLOCK));
        king.getEquipment().setChestplate(new org.bukkit.inventory.ItemStack(org.bukkit.Material.NETHERITE_CHESTPLATE));
        king.getEquipment().setItemInMainHand(new org.bukkit.inventory.ItemStack(org.bukkit.Material.GOLDEN_SWORD));

        Bukkit.broadcastMessage("§6⚡ A §lWraith King §6has risen in the acid storm!");
    }
}
