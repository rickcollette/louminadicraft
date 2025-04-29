package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Spawns Memory Fragments randomly near Rift areas.
 */
public class MemoryFragmentSpawner {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public MemoryFragmentSpawner(JavaPlugin plugin) {
        this.plugin = plugin;
        startSpawningTask();
    }

    private void startSpawningTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("world_existential");
                if (world == null) return;

                if (random.nextInt(3) != 0) return; // ~1/3 chance every cycle

                int x = random.nextInt(1000) - 500;
                int z = random.nextInt(1000) - 500;
                int y = world.getHighestBlockYAt(x, z) + 1;

                ItemStack fragment = new ItemStack(Material.AMETHYST_SHARD);
                Item item = world.dropItemNaturally(new org.bukkit.Location(world, x, y, z), fragment);
                item.setCustomName("§dFractured Memory");
                item.setCustomNameVisible(true);
                item.setGlowing(true);
            }
        }.runTaskTimer(plugin, 0L, 600L); // Check every 30 seconds
    }
}
