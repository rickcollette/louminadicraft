package com.louminadicraft.listener;

import com.louminadicraft.quest.MemoryQuestManager;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Collects memory fragments into player memory log.
 */
public class MemoryFragmentCollector implements Listener {

    private final JavaPlugin plugin;

    public MemoryFragmentCollector(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Item item = event.getItem();
        Player player = event.getPlayer();

        if (item.getItemStack().getType() == org.bukkit.Material.AMETHYST_SHARD
            && item.getCustomName() != null
            && item.getCustomName().contains("Fractured Memory")) {

            event.setCancelled(true);
            item.remove();

            MemoryQuestManager.getInstance().addMemoryFragment(player);
            player.sendMessage("§d🧠 You have recovered a Fractured Memory!");
        }
    }
}
