package com.louminadicraft.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.persistence.PersistentDataType;

/**
 * RiftAmulet - Special item crafted from Rift Essences
 */
public class RiftAmulet {

    public static ItemStack createRiftAmulet(JavaPlugin plugin) {
        ItemStack amulet = new ItemStack(Material.HEART_OF_THE_SEA, 1);
        ItemMeta meta = amulet.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§5Rift Amulet");
            meta.getPersistentDataContainer().set(
                new NamespacedKey(plugin, "rift_amulet"),
                PersistentDataType.INTEGER,
                1
            );
            amulet.setItemMeta(meta);
        }

        return amulet;
    }
}
