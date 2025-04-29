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
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * TempleMiniBossSpawner - Spawns stronger minibosses in BillyD serpent temples.
 */
public class TempleMiniBossSpawner implements Listener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public TempleMiniBossSpawner(JavaPlugin plugin) {
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

        // 10% chance to spawn a miniboss
        if (random.nextInt(10) != 0) {
            return;
        }

        Location bossSpawn = new Location(world,
            (chunk.getX() << 4) + 8,
            66,
            (chunk.getZ() << 4) + 8
        );

        Zombie boss = (Zombie) world.spawnEntity(bossSpawn, EntityType.ZOMBIE);
        boss.setCustomName("§5Temple Warden");
        boss.setCustomNameVisible(true);
        boss.setAdult();
        boss.setGlowing(true);
        boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(150.0);
        boss.setHealth(150.0);
        boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0);

        // Equip randomly
        boss.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
        boss.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        boss.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
    }
}
