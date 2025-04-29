package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * LoreBookManager - Places ancient lore books into randomly found chests
 */
public class LoreBookManager implements Listener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public LoreBookManager(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (!event.isNewChunk()) {
            return;
        }

        Chunk chunk = event.getChunk();
        String worldName = chunk.getWorld().getName().toLowerCase();

        boolean isBillyD = worldName.contains("billyd");
        boolean isMoneyShot = worldName.contains("moneyshot");
        boolean isConditions = worldName.contains("conditions");

        if (!(isBillyD || isMoneyShot || isConditions)) {
            return;
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = chunk.getWorld().getMinHeight(); y < chunk.getWorld().getMaxHeight(); y++) {
                    Block block = chunk.getBlock(x, y, z);

                    if (block.getType() == Material.CHEST) {
                        if (block.getState() instanceof Chest chest) {
                            if (random.nextInt(10) == 0) { // 10% chance per chest
                                chest.getInventory().addItem(generateLoreBook(worldName));
                            }
                        }
                    }
                }
            }
        }
    }

    private ItemStack generateLoreBook(String worldName) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        if (worldName.contains("billyd")) {
            meta.setTitle("Feathered Dreams");
            meta.setAuthor("Unknown Shaman");
            meta.addPage("Visions of serpents, stone, and the endless jungle skies.");
        } else if (worldName.contains("moneyshot")) {
            meta.setTitle("The Last Sermon");
            meta.setAuthor("Forgotten Preacher");
            meta.addPage("Gold rotted faster than the faithful. No absolution remains.");
        } else if (worldName.contains("conditions")) {
            meta.setTitle("Echoes of Iron");
            meta.setAuthor("Factory Ghost");
            meta.addPage("Factories wept before the forests reclaimed their skin.");
        }

        book.setItemMeta(meta);
        return book;
    }
}
