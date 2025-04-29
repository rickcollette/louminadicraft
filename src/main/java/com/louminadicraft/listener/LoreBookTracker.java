package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * LoreBookTracker - Tracks how many lore books each player finds per world.
 */
public class LoreBookTracker implements Listener {

    private final JavaPlugin plugin;
    private final NamespacedKey loreBookKey;

    public LoreBookTracker(JavaPlugin plugin) {
        this.plugin = plugin;
        this.loreBookKey = new NamespacedKey(plugin, "lorebooks_found");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem().getItemStack();

        if (item.getType() != Material.WRITTEN_BOOK) {
            return;
        }

        if (!item.getItemMeta().getDisplayName().toLowerCase().contains("lore book")) {
            return;
        }

        incrementLoreBooks(player);
    }

    private void incrementLoreBooks(Player player) {
        var data = player.getPersistentDataContainer();
        Integer found = data.getOrDefault(loreBookKey, PersistentDataType.INTEGER, 0);
        data.set(loreBookKey, PersistentDataType.INTEGER, found + 1);

        player.sendMessage("§d📖 You found a Lore Book! Total: " + (found + 1));
    }

    // Optional helper method for plugins or future stats
    public int getLoreBooksFound(Player player) {
        return player.getPersistentDataContainer().getOrDefault(loreBookKey, PersistentDataType.INTEGER, 0);
    }
}
