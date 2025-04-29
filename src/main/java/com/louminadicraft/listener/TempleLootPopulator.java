package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * TempleLootPopulator - Fills serpent temple chests with loot when chunks load
 */
public class TempleLootPopulator implements Listener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public TempleLootPopulator(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (!event.isNewChunk()) {
            return; // Only process newly generated chunks
        }

        Chunk chunk = event.getChunk();
        var world = chunk.getWorld();

        if (!world.getName().toLowerCase().contains("billyd")) {
            return;
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = world.getMinHeight(); y < world.getMaxHeight(); y++) {
                    Block block = chunk.getBlock(x, y, z);

                    if (block.getType() == Material.CHEST) {
                        if (block.getState() instanceof Chest chest) {
                            populateChest(chest.getInventory());
                        }
                    }
                }
            }
        }
    }

    private void populateChest(Inventory inv) {
        inv.clear();

        // Always some emeralds
        inv.addItem(new ItemStack(Material.EMERALD, 2 + random.nextInt(4)));

        // 50% chance enchanted book
        if (random.nextBoolean()) {
            inv.addItem(new ItemStack(Material.ENCHANTED_BOOK, 1));
        }

        // Rare golden apple
        if (random.nextInt(5) == 0) {
            inv.addItem(new ItemStack(Material.GOLDEN_APPLE, 1));
        }

        // Common moss or vines
        if (random.nextBoolean()) {
            inv.addItem(new ItemStack(Material.VINE, 8));
        }

        // Rare ancient artifact (trident)
        if (random.nextInt(20) == 0) {
            inv.addItem(new ItemStack(Material.TRIDENT, 1));
        }
    }
}
