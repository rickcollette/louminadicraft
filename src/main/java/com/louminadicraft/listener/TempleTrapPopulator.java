package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * TempleTrapPopulator - Adds random arrow booby traps inside serpent temples
 */
public class TempleTrapPopulator implements Listener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public TempleTrapPopulator(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (!event.isNewChunk()) {
            return;
        }

        var chunk = event.getChunk();
        var world = chunk.getWorld();

        if (!world.getName().toLowerCase().contains("billyd")) {
            return;
        }

        // Random chance to place a trap
        if (random.nextInt(5) != 0) {
            return;
        }

        int trapX = 8 + random.nextInt(5) - 2; // Near center
        int trapZ = 8 + random.nextInt(5) - 2;
        int trapY = 64; // Around jungle floor

        Block tripwireBlock = chunk.getBlock(trapX, trapY, trapZ);
        tripwireBlock.setType(Material.TRIPWIRE);

        Tripwire tripwire = (Tripwire) tripwireBlock.getBlockData();
        tripwire.setAttached(true);
        tripwireBlock.setBlockData(tripwire);

        Block dispenserBlock = chunk.getBlock(trapX, trapY, trapZ + 1);
        dispenserBlock.setType(Material.DISPENSER);

        var dispenser = (org.bukkit.block.Dispenser) dispenserBlock.getState();
        dispenser.getInventory().addItem(new org.bukkit.inventory.ItemStack(Material.ARROW, 5));
        dispenser.update();
    }
}
