package com.louminadicraft.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * RiftEssenceDropper - Drop essence shards from Rift Echoes
 */
public class RiftEssenceDropper implements Listener {

    public RiftEssenceDropper(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        var entity = event.getEntity();

        if (entity.getCustomName() != null && entity.getCustomName().contains("≡ Rift Echo ≡")) {
            event.getDrops().clear(); // clear normal drops
            event.getDrops().add(new ItemStack(Material.AMETHYST_SHARD, 4 + (int)(Math.random() * 4)));
        }
    }
}
