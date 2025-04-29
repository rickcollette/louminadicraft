package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * TempleBossSpawner - Spawns a mini-boss guardian inside some serpent temples
 */
public class TempleBossSpawner implements Listener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public TempleBossSpawner(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (!event.isNewChunk()) {
            return;
        }

        var chunk = event.getChunk();
        World world = chunk.getWorld();

        if (!world.getName().toLowerCase().contains("billyd")) {
            return;
        }

        // Small chance of spawning a boss
        if (random.nextInt(10) != 0) {
            return;
        }

        Location spawnLoc = new Location(world,
            (chunk.getX() << 4) + 8,
            65,
            (chunk.getZ() << 4) + 8
        );

        Zombie boss = (Zombie) world.spawnEntity(spawnLoc, EntityType.ZOMBIE);
        boss.setCustomName("§6Ancient Temple Guardian");
        boss.setCustomNameVisible(true);
        boss.setAdult();
        boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100.0);
        boss.setHealth(100.0);
        boss.getEquipment().setHelmet(new org.bukkit.inventory.ItemStack(Material.GOLDEN_HELMET));
        boss.getEquipment().setChestplate(new org.bukkit.inventory.ItemStack(Material.GOLDEN_CHESTPLATE));
        boss.getEquipment().setItemInHand(new org.bukkit.inventory.ItemStack(Material.GOLDEN_SWORD));
        boss.setGlowing(true);
    }
}
